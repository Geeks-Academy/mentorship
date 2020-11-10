package com.programmersonly.mentorship.mentors.repository;

import com.programmersonly.mentorship.mentors.entity.TemplateEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateEntityRepository extends JpaRepository<TemplateEntity, UUID> {
  boolean existsByEmail(String email);
}
