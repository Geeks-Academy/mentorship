package com.programmersonly.mentorship.template;

import com.programmersonly.mentorship.template.dto.CreateTemplateDto;

import java.util.UUID;

public interface TemplateService {
    void create(CreateTemplateDto createTemplateDto);
    void confirm(UUID templateId);
    void remove(UUID templateId);
}
