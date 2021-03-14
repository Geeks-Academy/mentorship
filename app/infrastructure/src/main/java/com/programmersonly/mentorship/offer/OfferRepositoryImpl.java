package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.exception.CannotFindOfferException;
import com.programmersonly.mentorship.offers.OfferRepository;
import com.programmersonly.mentorship.offers.OfferState;
import com.programmersonly.mentorship.offers.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class OfferRepositoryImpl implements OfferRepository {

    private final OfferDao offerDao;



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
    public Collection<Offer> getOffers() {
        Collection<Offer> list = new LinkedList<>();


        offerDao.findAll().forEach(e ->list.add(map(e)));
        return list;
    }

    @Override
    public Offer getOffer(UUID offerId) {
        OfferEntity entity = offerDao.findById(offerId).orElseThrow();
//                .orElseThrow(CannotFindOfferException::new);
        System.out.println(entity);

        return map(entity);
    }


    private Offer map(OfferEntity entity) {
        return Offer.builder()
                .offerId(entity.getOfferId())
                .ownerId(entity.getOwnerId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .requestSet(entity.getRequestSet())
                .attenderId(entity.getAttenderId())
                .canceledBy(entity.getCanceledBy())
                .gradeDate(entity.getGradeDate())
                .gradeValue(entity.getGradeValue())
                .state(entity.getState())
                .build();
    }

    private OfferEntity map (Offer offer){
       return OfferEntity.builder()
                .ownerId(offer.getOwnerId())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .attenderId(offer.getAttenderId())
                .canceledBy(offer.getCanceledBy())
                .gradeDate(offer.getGradeDate())
                .requestSet(offer.getRequestSet())
                .offerId(offer.getOfferId())
                .gradeValue(offer.getGradeValue())
                .state(offer.getState())
                .build();
    }
}
