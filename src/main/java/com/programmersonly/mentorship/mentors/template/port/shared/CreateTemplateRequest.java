package com.programmersonly.mentorship.mentors.template.port.shared;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTemplateRequest {
  private UUID userId;
  private String email;
  private String fullName;
}
