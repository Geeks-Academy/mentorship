package com.geeksacagemy.mentorship.template;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface TemplateDao extends CrudRepository<TemplateEntity, UUID> {

    @Modifying
    @Query("update TemplateEntity e " +
            "set e.status = :status " +
            "where e.id = :id")
    void updateTemplateStatus(UUID id, TemplateStatus status);

}
