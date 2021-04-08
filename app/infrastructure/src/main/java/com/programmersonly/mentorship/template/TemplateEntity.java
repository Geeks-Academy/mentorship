package com.programmersonly.mentorship.template;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "TEMPLATE")
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

  @Enumerated(EnumType.STRING)
  private TemplateStatus status;
}
