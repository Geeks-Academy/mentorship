package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.exception.AttenderNotFound;
import com.programmersonly.mentorship.exception.CannotFindOfferException;
import com.programmersonly.mentorship.offers.Attender;
import com.programmersonly.mentorship.offers.Attender.Status;
import com.programmersonly.mentorship.offers.Offer;
import com.programmersonly.mentorship.offers.OfferRepository;
import com.programmersonly.mentorship.offers.OfferState;
import com.programmersonly.mentorship.offers.dto.AddAttenderDto;
import com.programmersonly.mentorship.offers.dto.ConfirmAttenderDto;
import com.programmersonly.mentorship.offers.dto.CreateOfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class OfferRepositoryImpl implements OfferRepository {

    private final OfferDao offerDao;
    private final AttenderDao attenderDao;

    @Override
    public void create(CreateOfferDto createOfferDto) {
        OfferEntity offerEntity = OfferEntity.builder()
                .ownerId(createOfferDto.getOwnerId())
                .startDate(createOfferDto.getStartDate())
                .endDate(createOfferDto.getEndDate())
                .state(OfferState.NEW)
                .build();
        offerDao.save(offerEntity);
    }

    @Override
    public void save(Offer offer) {
         OfferEntity entity = map(offer);
         offerDao.save(entity);
    }


    @Override
    public void addAttender(AddAttenderDto addAttenderDto) {
        Optional<OfferEntity> offerEntity = offerDao.findById(addAttenderDto.getOfferId());
        if (offerEntity.isPresent()) {
            AttenderEntity attenderEntity = AttenderEntity.builder()
                    .offer(offerEntity.get())
                    .status(Status.REQUESTED)
                    .attenderId(addAttenderDto.getAttenderId())
                    .build();
            attenderDao.save(attenderEntity);
        } else {
            throw new CannotFindOfferException();
        }
    }

    @Override
    public List<Offer> getOffers() {
        return offerDao.getAllOffers().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void cancel(UUID offerId) {
        offerDao.updateStatus(offerId, OfferState.CANCELLED);
    }

    @Override
    public void confirmAttender(ConfirmAttenderDto confirmAttenderDto) {
        Optional<AttenderEntity> optAttender = attenderDao.findAttender(confirmAttenderDto.getAttenderId(), confirmAttenderDto.getOfferId());
        if (optAttender.isPresent()) {
            AttenderEntity acceptedAttender = optAttender.get().toBuilder()
                    .status(Status.ACCEPTED)
                    .build();
            attenderDao.save(acceptedAttender);
        }
        throw new AttenderNotFound();
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
                .attenders(mapToModel(entity.getAttenders()))
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
                .attenders(mapToEntity(offer.getAttenders()))
                .offerId(offer.getOfferId())
                .state(offer.getState())
                .build();
    }
}
