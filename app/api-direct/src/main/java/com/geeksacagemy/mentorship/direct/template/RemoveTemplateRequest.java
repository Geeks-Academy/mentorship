package com.geeksacagemy.mentorship.direct.template;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class RemoveTemplateRequest {
  private UUID templateId;
}
