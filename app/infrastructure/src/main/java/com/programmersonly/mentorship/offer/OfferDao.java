package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.offers.OfferState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

interface OfferDao extends CrudRepository<OfferEntity, UUID> {

    @Query("SELECT offer from OfferEntity offer ")
    List<OfferEntity> getAllOffers();

    @Query("UPDATE OfferEntity SET state = :newState where offerId = :offerId ")
    @Modifying
    void updateStatus(@Param("offerId") UUID offerId, @Param("newState") OfferState newState);
}
