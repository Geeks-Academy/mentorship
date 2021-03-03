package com.programmersonly.mentorship.template;

import java.util.Optional;
import java.util.UUID;

public interface TemplateRepository {

    void create(Template template);

    Optional<Template> findById(UUID templateId);

    void confirm(UUID templateId);

    void deleteById(UUID templateId);
}
