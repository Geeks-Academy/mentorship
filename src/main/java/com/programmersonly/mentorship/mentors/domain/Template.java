package com.programmersonly.mentorship.mentors.domain;

import com.programmersonly.mentorship.mentors.entity.TemplateStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Template {

  private TemplateId id;
  private String fullName;
  private String email;
  private TemplateStatus status;

  public boolean confirm() {
    if (status == TemplateStatus.CONFIRMED) {
      return false;
    }
    status = TemplateStatus.CONFIRMED;
    return true;
  }
}
