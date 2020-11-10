package com.programmersonly.mentorship.mentors.service;

import com.programmersonly.mentorship.mentors.controller.request.ConfirmTemplateRequest;
import com.programmersonly.mentorship.mentors.controller.request.CreateTemplateRequest;
import com.programmersonly.mentorship.mentors.controller.request.RemoveTemplateRequest;
import com.programmersonly.mentorship.mentors.domain.Template;
import com.programmersonly.mentorship.mentors.domain.TemplateId;
import com.programmersonly.mentorship.mentors.entity.TemplateStatus;
import com.programmersonly.mentorship.mentors.repository.TemplateRepository;
import com.programmersonly.mentorship.mentors.service.exception.EmailAlreadyUsed;
import com.programmersonly.mentorship.mentors.service.exception.ForbiddenTemplateStatusToDelete;
import com.programmersonly.mentorship.mentors.service.exception.TemplateAlreadyConfirmed;
import com.programmersonly.mentorship.mentors.service.exception.TemplateNotFound;
import com.programmersonly.mentorship.mentors.service.mapper.TemplateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

  private final TemplateRepository repository;
  private final TemplateMapper mapper;

  public void create(CreateTemplateRequest request) {
    Template template = mapper.mapToTemplate(request);
    verifyEmailUniqueness(template.getEmail());
    repository.save(template);
  }

  public void confirm(ConfirmTemplateRequest request) {
    Template template = repository.findById(TemplateId.createFrom(request.getTemplateId()))
        .orElseThrow(() -> new TemplateNotFound(request.getTemplateId()));
    if(!template.confirm()) {
      throw new TemplateAlreadyConfirmed(request.getTemplateId());
    }
    repository.save(template);
  }

  public void remove(RemoveTemplateRequest request) {
    TemplateId templateId = TemplateId.createFrom(request.getTemplateId());
    Template template = repository.findById(templateId)
        .orElseThrow(() -> new TemplateNotFound(request.getTemplateId()));

    if (template.getStatus() == TemplateStatus.CREATED) {
      repository.deleteById(templateId);
    } else {
      throw new ForbiddenTemplateStatusToDelete(template.getStatus());
    }
  }

  private void verifyEmailUniqueness(String email) {
    if(repository.existsByEmail(email)) {
      throw new EmailAlreadyUsed(email);
    }
  }
}
