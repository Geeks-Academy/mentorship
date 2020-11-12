package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.commons.exception.BasicErrorResponse;
import com.programmersonly.mentorship.commons.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class SpringTemplateQuery implements TemplateQuery {

    private final TemplateEntityRepository repository;

    public TemplateResponse findByTemplateId(UUID templateId) {
        return repository.findById(templateId)
                .map(entity -> TemplateResponse.builder().fullName(entity.getFullName()).userId(entity.getUserId()).build())
                .orElseThrow(() -> BusinessException.exception404(new BasicErrorResponse("MS-02", "Cannot find template")));
    }
}
