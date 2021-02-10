package com.programmersonly.mentorship.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
class TemplateEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Type(type = "uuid-char")
  private UUID id;

  @Type(type = "uuid-char")
  private UUID userId;
  private String fullName;
  private String email;
  private TemplateStatus status;
}
