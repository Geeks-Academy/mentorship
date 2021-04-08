package com.programmersonly.mentorship.offer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

interface AttenderDao extends CrudRepository<AttenderEntity, UUID> {

    @Query("SELECT attender FROM AttenderEntity attender " +
            "WHERE attender.id = :attenderId " +
            "AND attender.offer.offerId = :offerId")
    Optional<AttenderEntity> findAttender(@Param("attenderId") UUID attenderId, @Param("offerId") UUID offerId);
}
