package com.programmersonly.mentorship.mentors.template;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Component
@AllArgsConstructor
class TemplateRepository {

    private final TemplateEntityRepository templateEntityRepository;

    public Try<Template> saveTemplate(Template template) {
        return Try.of(() -> {
            TemplateEntity entity = templateEntityRepository.save(new TemplateEntity(template.getDescription()));
            return entity.toTemplate();
        });
    }
}

interface TemplateEntityRepository extends CrudRepository<TemplateEntity, UUID> {

}

@Entity(name = "template")
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class TemplateEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID templateId;

    private String description;

    TemplateEntity(String description) {
        this.description = description;
    }

    Template toTemplate() {
        return new Template(templateId, description);
    }
}
