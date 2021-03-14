package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.dto.*;

import java.util.Collection;
import java.util.UUID;

public interface OfferService {

    void create(CreateOfferDto createOfferDto);

    Collection<Offer> offers();

    void addAttender(UUID offerId, AddAttenderDto addAttenderDto);

    Offer getOffer(UUID offerId);

    void cancel(UUID offerId, CancelOfferDto cancelOfferDto);

    void confirmAttender(UUID offerId, ConfirmAttenderDto confirmAttenderDto);

    void gradeOffer(UUID offerId, GradeOfferDto gradeOfferDto);

/*    void confirmAttender(UUID offerId, ConfirmAttenderRequest dto);


    void grade(UUID offerId, GradeOfferRequest dto);


    Collection<UUID> getRequestedOfferAttenders(UUID offerId);

    void checkIfConsultationStarted();

    void checkIfTimeForGradingPassed();*/
}
