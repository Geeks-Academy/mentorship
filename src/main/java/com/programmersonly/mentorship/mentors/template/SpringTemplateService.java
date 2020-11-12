package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringTemplateService implements TemplateService {

  private final TemplateEntityRepository repository;

  public void create(CreateTemplateRequest request) {
    repository.save(new TemplateEntity(request.getUserId(), request.getFullName(), request.getEmail(), TemplateStatus.CREATED));
  }

  //TODO @Transactional doesn't work by default, why?
  public void confirm(ConfirmTemplateRequest request) {
    TemplateEntity template = repository.findById(request.getTemplateId())
        .orElseThrow(NotFoundException::new);

    template.confirm();
    repository.save(template);
  }

  public void remove(RemoveTemplateRequest request) {
    repository.findById(request.getTemplateId())
            .filter(templateEntity -> templateEntity.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(NotFoundException::new);

    repository.deleteById(request.getTemplateId());
  }
}
