package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.BasicErrorResponse;
import com.programmersonly.mentorship.commons.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringTemplateService implements TemplateService {

  private final TemplateEntityRepository repository;

  public void create(CreateTemplateRequest request) {
    repository.save(new TemplateEntity(request.getUserId(), request.getFullName(), request.getEmail(), TemplateStatus.CREATED));
  }

  public void confirm(ConfirmTemplateRequest request) {
    TemplateEntity template = repository.findById(request.getTemplateId())
        .orElseThrow(() -> BusinessException.exception404(new BasicErrorResponse("MS-02", "Cannot find template")));

    template.confirm();
    repository.save(template);
  }

  public void remove(RemoveTemplateRequest request) {
    repository.findById(request.getTemplateId())
            .filter(templateEntity -> templateEntity.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(() -> BusinessException.exception404(new BasicErrorResponse("MS-02", "Cannot find template")));

    repository.deleteById(request.getTemplateId());
  }
}
