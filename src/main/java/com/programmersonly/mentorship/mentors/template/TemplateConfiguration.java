package com.programmersonly.mentorship.mentors.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TemplateConfiguration {

    
    @Bean
    TemplateFacade templateFacade(TemplateEntityRepository repository) {
        return new TemplateFacade(repository);
    }

}
