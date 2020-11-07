package com.programmersonly.mentorship.mentors.template;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TemplateService {

    private final TemplateRepository repository;

    public CreatedTemplateDto createTemplate(CreateTemplateDto dto) {
        return repository
                .saveTemplate(new Template(dto.getDescription()))
                .map(template -> new CreatedTemplateDto(template.getTemplateId().getTemplateId()))
                .getOrElseThrow(() -> new IllegalArgumentException("Cannot create template."));
    }
}
