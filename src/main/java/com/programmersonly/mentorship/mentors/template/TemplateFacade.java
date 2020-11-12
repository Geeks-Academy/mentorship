package com.programmersonly.mentorship.mentors.template;


import java.util.UUID;

class TemplateFacade {

    private final TemplateQuery templateQuery;
    private final TemplateService templateService;

    public TemplateFacade(TemplateEntityRepository repository) {
        this.templateQuery = new SpringTemplateQuery(repository);
        this.templateService = new SpringTemplateService(repository);
    }

    public TemplateResponse findByTemplateId(UUID templateId) {
        return templateQuery.findByTemplateId(templateId);
    }

    public void create(CreateTemplateRequest request) {
        templateService.create(request);
    }

    public void confirm(ConfirmTemplateRequest request) {
        templateService.confirm(request);
    }

    public void remove(RemoveTemplateRequest request) {
        templateService.remove(request);
    }
}
