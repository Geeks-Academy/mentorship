package com.programmersonly.mentorship.offer;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface OfferDao extends CrudRepository<OfferEntity, UUID> {


}
