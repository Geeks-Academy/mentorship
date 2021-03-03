package com.programmersonly.mentorship.direct.template;

import lombok.Data;

import java.util.UUID;

@Data
class RemoveTemplateRequest {
  private UUID templateId;
}
