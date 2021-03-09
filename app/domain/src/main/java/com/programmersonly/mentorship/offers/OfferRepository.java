package com.programmersonly.mentorship.offers;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface OfferRepository {
    void create(Offer offer);

    Collection<Offer> getOffers();

    Offer getOffer(UUID offerId);

    void updateRequestSet(Offer offer);
}
