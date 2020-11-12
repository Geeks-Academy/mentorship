package com.programmersonly.mentorship.mentors.template;

import com.programmersonly.mentorship.mentors.template.port.primary.TemplateQuery;
import com.programmersonly.mentorship.mentors.template.port.primary.TemplateService;
import lombok.Getter;

@Getter
public class TemplateFacade {

    private final TemplateQuery templateQuery;
    private final TemplateService templateService;


    public TemplateFacade(TemplateEntityRepository repository) {
        this.templateQuery = new SpringTemplateQuery(repository);
        this.templateService = new SpringTemplateService(repository);
    }
}
