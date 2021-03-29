package com.programmersonly.mentorship.offers;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface OfferRepository {
    void create(Offer offer);

    void save(Offer odder);

    Collection<Offer> getOffers();

    void addAttender(UUID offerId, Attender attender);

    Offer getOffer(UUID offerId);

}
