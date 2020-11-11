package com.programmersonly.mentorship.mentors.template;

import lombok.Data;

import java.util.UUID;

@Data
public class ConfirmTemplateRequest {
  private UUID templateId;
}
