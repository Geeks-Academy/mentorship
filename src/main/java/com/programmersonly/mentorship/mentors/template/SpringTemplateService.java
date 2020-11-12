package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.NotFoundException;
import com.programmersonly.mentorship.mentors.template.TemplateEntity;
import com.programmersonly.mentorship.mentors.template.TemplateEntityRepository;
import com.programmersonly.mentorship.mentors.template.TemplateStatus;
import com.programmersonly.mentorship.mentors.template.port.primary.TemplateService;
import com.programmersonly.mentorship.mentors.template.port.shared.ConfirmTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.CreateTemplateRequest;
import com.programmersonly.mentorship.mentors.template.port.shared.RemoveTemplateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringTemplateService implements TemplateService {

  private final TemplateEntityRepository repository;

  public void create(CreateTemplateRequest request) {
    repository.save(new TemplateEntity(request.getUserId(), request.getFullName(), request.getEmail(), TemplateStatus.CREATED));
  }

  public void confirm(ConfirmTemplateRequest request) {
    TemplateEntity template = repository.findById(request.getTemplateId())
        .orElseThrow(NotFoundException::new);

    template.confirm();
  }

  public void remove(RemoveTemplateRequest request) {
    repository.findById(request.getTemplateId())
            .filter(templateEntity -> templateEntity.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(NotFoundException::new);

    repository.deleteById(request.getTemplateId());
  }
}
