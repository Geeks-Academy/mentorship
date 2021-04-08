package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.AddAttenderDto;
import com.programmersonly.mentorship.offers.dto.ConfirmAttenderDto;
import com.programmersonly.mentorship.offers.dto.CreateOfferDto;

import java.util.List;
import java.util.UUID;

public interface OfferRepository {
    void create(CreateOfferDto createOfferDto);

    void save(Offer odder);

    List<Offer> getOffers();

    void addAttender(AddAttenderDto addAttenderDto);

    Offer getOffer(UUID offerId);

    void cancel(UUID offerId);

    void confirmAttender(ConfirmAttenderDto confirmAttenderDto);
}
