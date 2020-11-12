package com.programmersonly.mentorship.mentors.template;

import lombok.Data;

import java.util.UUID;

@Data
class ConfirmTemplateRequest {
  private UUID templateId;
}
