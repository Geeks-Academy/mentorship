package com.programmersonly.mentorship.mentors.template;

import lombok.Data;

import java.util.UUID;

@Data
class CreateTemplateRequest {
  private UUID userId;
  private String email;
  private String fullName;
}