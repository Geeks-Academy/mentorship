package com.programmersonly.mentorship.mentors.template;

import java.util.Optional;
import java.util.UUID;

interface TemplateQuery {
    Optional<TemplateEntity> findById(UUID templateId);
}
