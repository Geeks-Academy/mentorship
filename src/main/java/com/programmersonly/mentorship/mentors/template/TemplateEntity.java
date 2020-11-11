package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.programmersonly.mentorship.commons.exception.BasicErrorResponse;
import com.programmersonly.mentorship.commons.exception.BusinessException;
import com.programmersonly.mentorship.mentors.template.TemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpStatus;

@Entity(name = "Template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  private UUID userId;
  private String fullName;
  private String email;
  private TemplateStatus status;

  public TemplateEntity(UUID userId, String fullName, String email, TemplateStatus status) {
    this.userId = userId;
    this.fullName = fullName;
    this.email = email;
    this.status = status;
  }

  public void confirm() {
    if(this.status != TemplateStatus.CREATED) {
      throw BusinessException.exception409(new BasicErrorResponse("MS-01", "Cannot confirm"));
    }

    this.status = TemplateStatus.CONFIRMED;
  }
}
