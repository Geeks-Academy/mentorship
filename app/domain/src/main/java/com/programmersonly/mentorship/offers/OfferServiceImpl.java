package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.exception.AddAttenderException;
import com.programmersonly.mentorship.offers.dto.AddAttenderDto;
import com.programmersonly.mentorship.offers.dto.CreateOfferDto;
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
    public void addAttender(UUID offerId, AddAttenderDto dto) {
        Offer offer = offerRepository.getOffer(offerId);
        if (!offer.getRequestSet().add(dto.getAttenderId())){
            throw new AddAttenderException();
        }

        offerRepository.updateRequestSet(offer);
    }

    @Override
    public Offer getOffer(UUID offerId) {
        return  offerRepository.getOffer(offerId);
    }


}
