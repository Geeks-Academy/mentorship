package com.programmersonly.mentorship.mentors.template;

import lombok.Data;

import java.util.UUID;

@Data
public class RemoveTemplateRequest {
  private UUID templateId;
}
