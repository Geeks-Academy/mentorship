package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.AddAttenderDto;
import com.programmersonly.mentorship.offers.dto.CreateOfferDto;

import java.util.Collection;
import java.util.UUID;

public interface OfferService {

    void create(CreateOfferDto createOfferDto);

    Collection<Offer> offers();

    void addAttender(UUID offerId, AddAttenderDto dto);

    Offer getOffer(UUID offerId);

/*    void confirmAttender(UUID offerId, ConfirmAttenderRequest dto);

    void cancel(UUID offerId, CancelOfferRequest dto);

    void grade(UUID offerId, GradeOfferRequest dto);


    Collection<UUID> getRequestedOfferAttenders(UUID offerId);

    void checkIfConsultationStarted();

    void checkIfTimeForGradingPassed();*/
}
