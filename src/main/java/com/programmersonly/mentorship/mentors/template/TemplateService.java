package com.programmersonly.mentorship.mentors.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

  private final TemplateEntityRepository repository;

  public void create(CreateTemplateRequest request) {
    repository.save(new TemplateEntity(request.getUserId(), request.getFullName(), request.getEmail(), TemplateStatus.CREATED));
  }

  public void confirm(ConfirmTemplateRequest request) {
    TemplateEntity template = repository.findById(request.getTemplateId())
        .orElseThrow(() -> new IllegalArgumentException(""));

    template.confirm();
  }

  public void remove(RemoveTemplateRequest request) {
    repository.findById(request.getTemplateId())
            .filter(templateEntity -> templateEntity.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(() -> new IllegalArgumentException(""));

    repository.deleteById(request.getTemplateId());
  }
}
