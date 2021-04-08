package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.*;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    void create(CreateOfferDto createOfferDto);

    List<Offer> getOffers();

    void addAttender(AddAttenderDto addAttenderDto);

    Offer getOffer(UUID offerId);

    void cancel(CancelOfferDto cancelOfferDto);

    void confirmAttender(ConfirmAttenderDto confirmAttenderDto);

    void gradeOffer(UUID offerId, GradeOfferDto gradeOfferDto);

/*    void confirmAttender(UUID offerId, ConfirmAttenderRequest dto);


    void grade(UUID offerId, GradeOfferRequest dto);


    Collection<UUID> getRequestedOfferAttenders(UUID offerId);

    void checkIfConsultationStarted();

    void checkIfTimeForGradingPassed();*/
}
