package com.programmersonly.mentorship.mentors.service.mapper;

import com.programmersonly.mentorship.mentors.controller.request.CreateTemplateRequest;
import com.programmersonly.mentorship.mentors.domain.Template;
import com.programmersonly.mentorship.mentors.domain.TemplateId;
import com.programmersonly.mentorship.mentors.entity.TemplateEntity;
import com.programmersonly.mentorship.mentors.entity.TemplateStatus;
import org.springframework.stereotype.Component;

@Component
public class TemplateMapper {

  public Template mapToTemplate(CreateTemplateRequest request) {
    return Template.builder()
        .email(request.getEmail())
        .status(TemplateStatus.CREATED)
        .fullName(request.getFullName())
        .build();
  }

  public Template mapToTemplate(TemplateEntity entity) {
    return Template.builder()
        .id(TemplateId.createFrom(entity.getId()))
        .email(entity.getEmail())
        .status(TemplateStatus.CREATED)
        .fullName(entity.getFullName())
        .build();
  }

  public TemplateEntity mapToTemplateEntity(Template template) {
    return TemplateEntity.builder()
        .id(template.getId() != null ? template.getId().getValue(): null)
        .email(template.getEmail())
        .fullName(template.getFullName())
        .status(template.getStatus())
        .build();
  }
}
