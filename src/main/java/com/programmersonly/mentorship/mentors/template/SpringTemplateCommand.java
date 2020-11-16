package com.programmersonly.mentorship.mentors.template;

import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringTemplateCommand implements TemplateCommand {

    private final TemplateEntityRepository repository;

    @Override
    public void save(TemplateEntity templateEntity) {
        repository.save(templateEntity);
    }

    @Override
    public void deleteById(UUID templateId) {
        repository.deleteById(templateId);
    }
}
