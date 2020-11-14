package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.BasicErrorResponse;
import com.programmersonly.mentorship.commons.exception.BusinessException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringTemplateService implements TemplateService {

  private final TemplateQuery queryRepository;
  private final TemplateCommand commandRepository;

  public void create(CreateTemplateRequest request) {
    commandRepository.save(new TemplateEntity(request.getUserId(), request.getFullName(), request.getEmail(), TemplateStatus.CREATED));
  }

  @Transactional
  public void confirm(ConfirmTemplateRequest request) {
    TemplateEntity template = queryRepository.findById(request.getTemplateId())
        .orElseThrow(() -> BusinessException
            .ex404(new BasicErrorResponse("MS-02", "Cannot find template")));
    template.confirm();
  }

  public void remove(RemoveTemplateRequest request) {
    TemplateEntity entity = queryRepository.findById(request.getTemplateId())
        .filter(templateEntity -> templateEntity.getStatus().equals(TemplateStatus.CREATED))
        .orElseThrow(() -> BusinessException
            .ex404(new BasicErrorResponse("MS-02", "Cannot find template")));
    commandRepository.deleteById(entity.getId());
  }
}
