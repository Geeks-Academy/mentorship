package com.programmersonly.mentorship.mentors.template.port.shared;

import lombok.Data;

import java.util.UUID;

@Data
public class ConfirmTemplateRequest {
  private UUID templateId;
}
