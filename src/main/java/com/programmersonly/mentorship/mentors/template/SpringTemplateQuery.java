package com.programmersonly.mentorship.mentors.template;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class SpringTemplateQuery implements TemplateQuery {

    private final TemplateEntityRepository repository;

    public Optional<TemplateEntity> findById(UUID templateId) {
        return repository.findById(templateId);
    }
}
