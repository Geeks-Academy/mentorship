package com.programmersonly.mentorship.mentors.repository;

import com.programmersonly.mentorship.mentors.domain.Template;
import com.programmersonly.mentorship.mentors.domain.TemplateId;
import com.programmersonly.mentorship.mentors.service.mapper.TemplateMapper;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TemplateRepository {

  private final TemplateEntityRepository repository;
  private final TemplateMapper mapper;

  public void save(Template template) {
    repository.save(mapper.mapToTemplateEntity(template));
  }

  public void deleteById(TemplateId id) {
    repository.deleteById(id.getValue());
  }

  public Optional<Template> findById(TemplateId id) {
    return repository.findById(id.getValue())
        .map(mapper::mapToTemplate);
  }

  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }
}
