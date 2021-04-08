package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public void create(CreateOfferDto createOfferDto) {
        offerRepository.create(createOfferDto);
    }

    @Override
    public List<Offer> getOffers() {
        return offerRepository.getOffers();
    }

    @Override
    public void addAttender(AddAttenderDto addAttenderDto) {
       offerRepository.addAttender(addAttenderDto);
    }

    @Override
    public Offer getOffer(UUID offerId) {
        return offerRepository.getOffer(offerId);
    }

    @Override
    public void cancel(CancelOfferDto cancelOfferDto) {
        offerRepository.cancel(cancelOfferDto.getOfferId());
    }

    @Override
    public void confirmAttender(ConfirmAttenderDto confirmAttenderDto) {
        offerRepository.confirmAttender(confirmAttenderDto);
    }

    @Override
    public void gradeOffer(UUID offerId, GradeOfferDto gradeOfferDto) {
        Offer offer = offerRepository.getOffer(offerId);
//        offer.grade(gradeOfferDto.getGradeValue(), gradeOfferDto.getAttenderID());

    }
}
