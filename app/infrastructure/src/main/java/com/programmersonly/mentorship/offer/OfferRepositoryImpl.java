package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.exception.CannotFindOfferException;
import com.programmersonly.mentorship.offers.Attender;
import com.programmersonly.mentorship.offers.OfferRepository;
import com.programmersonly.mentorship.offers.OfferState;
import com.programmersonly.mentorship.offers.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class OfferRepositoryImpl implements OfferRepository {

    private final OfferDao offerDao;
    private final AttenderDao attenderDao;


    @Override
    public void create(Offer offer) {

        OfferEntity offerEntity = OfferEntity.builder()
                .ownerId(offer.getOwnerId())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .state(OfferState.NEW_OFFER)
                .build();

        offerDao.save(offerEntity);

        OfferEntity entity = offerDao.findAll().iterator().next();
        System.out.println(entity);
    }

    @Override
    public void save(Offer offer) {
         OfferEntity entity = map(offer);
         offerDao.save(entity);
    }


    @Override
    public void addAttender(UUID offerId, Attender attender) {
        Optional<OfferEntity> offerEntity = offerDao.findById(offerId);

        AttenderEntity attenderEntity = AttenderEntity.builder()
                .offer(offerEntity.get())
                .status(attender.getStatus())
                .attenderId(attender.getAttenderId())
                .build();
        attenderDao.save(attenderEntity);
    }

    @Override
    public Collection<Offer> getOffers() {
        Collection<Offer> list = new LinkedList<>();


        offerDao.findAll().forEach(e ->list.add(map(e)));
        return list;
    }

    @Override
    public Offer getOffer(UUID offerId) {
        OfferEntity entity = offerDao.findById(offerId)
                .orElseThrow(CannotFindOfferException::new);
        return map(entity);
    }


    private Offer map(OfferEntity entity) {
        return Offer.builder()
                .offerId(entity.getOfferId())
                .ownerId(entity.getOwnerId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .requestSet(mapToModel(entity.getRequestSet()))
                .attenderId(entity.getAttenderId())
                .canceledBy(entity.getCanceledBy())
                .gradeDate(entity.getGradeDate())
                .gradeValue(entity.getGradeValue())
                .state(entity.getState())
                .build();
    }

    private Set<Attender> mapToModel(Set<AttenderEntity> attenderEntity) {
        return attenderEntity.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    private Attender map(AttenderEntity entity) {
        return Attender.builder()
                .status(entity.getStatus())
                .attenderId(entity.getAttenderId())
                .id(entity.getId())
                .build();
    }

    private AttenderEntity map(Attender attender) {
        return AttenderEntity.builder()
                .status(attender.getStatus())
                .id(attender.getId())
                .attenderId(attender.getAttenderId())
                .build();
    }

    private Set<AttenderEntity> mapToEntity(Set<Attender> attenders) {
        return attenders.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    private OfferEntity map (Offer offer){
       return OfferEntity.builder()
                .ownerId(offer.getOwnerId())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .attenderId(offer.getAttenderId())
                .canceledBy(offer.getCanceledBy())
                .gradeDate(offer.getGradeDate())
                .requestSet(mapToEntity(offer.getRequestSet()))
                .offerId(offer.getOfferId())
                .gradeValue(offer.getGradeValue())
                .state(offer.getState())
                .build();
    }
}
