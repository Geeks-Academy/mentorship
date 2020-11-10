package com.programmersonly.mentorship.mentors.service.exception;

import com.programmersonly.mentorship.mentors.entity.TemplateStatus;

public class ForbiddenTemplateStatusToDelete extends RuntimeException {
  public ForbiddenTemplateStatusToDelete(TemplateStatus status) {
    super(String.format("Unable to delete template with status %s", status.toString()));
  }
}
