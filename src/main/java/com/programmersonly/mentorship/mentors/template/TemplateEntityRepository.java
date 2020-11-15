package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface TemplateEntityRepository extends CrudRepository<TemplateEntity, UUID> {

}
