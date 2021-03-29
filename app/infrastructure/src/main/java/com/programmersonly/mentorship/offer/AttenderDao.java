package com.programmersonly.mentorship.offer;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface AttenderDao extends CrudRepository<AttenderEntity, UUID> {


}
