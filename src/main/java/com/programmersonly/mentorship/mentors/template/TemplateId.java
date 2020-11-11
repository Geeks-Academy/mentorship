package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;
import lombok.Data;

@Data
public class TemplateId {
  private UUID value;

  private TemplateId(UUID value) {
    this.value = value;
  }

  public static TemplateId create() {
    return new TemplateId(UUID.randomUUID());
  }

  public static TemplateId createFrom(String id) {
    return new TemplateId(UUID.fromString(id));
  }

  public static TemplateId createFrom(UUID id) {
    return new TemplateId(id);
  }
}
