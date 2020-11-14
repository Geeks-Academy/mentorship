package com.programmersonly.mentorship.mentors.template;

class TemplateFacade {

    private final TemplateService templateService;

    public TemplateFacade(TemplateEntityRepository repository) {
        this.templateService = new SpringTemplateService(
            new SpringTemplateQuery(repository),
            new SpringTemplateCommand(repository));
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
