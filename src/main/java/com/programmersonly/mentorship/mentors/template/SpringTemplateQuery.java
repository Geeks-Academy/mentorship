package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class SpringTemplateQuery implements TemplateQuery {

    private final TemplateEntityRepository repository;

    public TemplateView findByTemplateId(UUID templateId) {
        return repository.findById(templateId)
                .map(entity -> TemplateView.builder().fullName(entity.getFullName()).userId(entity.getUserId()).build())
                .orElseThrow(NotFoundException::new);
    }
}
