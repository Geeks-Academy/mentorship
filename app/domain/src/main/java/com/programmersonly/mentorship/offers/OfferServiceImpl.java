package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public void create(CreateOfferDto createOfferDto) {
        Offer offer = Offer.builder()
                .ownerId(createOfferDto.getOwnerId())
                .startDate(createOfferDto.getStartDate())
                .endDate(createOfferDto.getEndDate())
                .state(OfferState.NEW_OFFER)
                .build();
        offerRepository.create(offer);
    }

    @Override
    public Collection<Offer> offers() {
        return offerRepository.getOffers();
    }

    @Override
    public void addAttender(UUID offerId, AddAttenderDto addAttenderDto) {
        Attender attender = Attender.builder()
                .attenderId(addAttenderDto.getAttenderId())
                .status(Attender.Status.REQUESTED)
                .build();
       offerRepository.addAttender(offerId, attender);
    }

    @Override
    public Offer getOffer(UUID offerId) {
        return  offerRepository.getOffer(offerId);
    }

    @Override
    public void cancel(UUID offerId, CancelOfferDto cancelOfferDto) {
        Offer offer = getOffer(offerId);
        offer.cancel(cancelOfferDto.getCancelBy());
        offerRepository.save(offer);
    }

    @Override
    public void confirmAttender(UUID offerId, ConfirmAttenderDto confirmAttenderDto) {
        Offer offer =offerRepository.getOffer(offerId);
//        offer.confirmAttender(confirmAttenderDto.getAttenderId());
        offerRepository.save(offer);
    }

    @Override
    public void gradeOffer(UUID offerId, GradeOfferDto gradeOfferDto) {
        Offer offer = offerRepository.getOffer(offerId);
//        offer.grade(gradeOfferDto.getGradeValue(), gradeOfferDto.getAttenderID());

    }
}
