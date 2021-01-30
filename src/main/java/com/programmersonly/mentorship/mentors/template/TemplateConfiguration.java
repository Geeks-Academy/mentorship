package com.programmersonly.mentorship.mentors.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TemplateConfiguration {

    
    @Bean
    TemplateRepository repository(TemplateEntityRepository repository){
        return new TemplateRepository(repository);
    }

    @Bean
    TemplateService templateService(TemplateRepository repository){
        return new TemplateService(repository);
    }

}
