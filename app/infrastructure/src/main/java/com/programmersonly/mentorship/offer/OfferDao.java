package com.programmersonly.mentorship.offer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

interface OfferDao extends CrudRepository<OfferEntity, UUID> {


}
