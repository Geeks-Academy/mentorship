package com.programmersonly.mentorship.mentors.controller.request;

import lombok.Data;

@Data
public class CreateTemplateRequest {
  private String userId;
  private String email;
  private String fullName;
}
