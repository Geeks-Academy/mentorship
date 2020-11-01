package com.programmersonly.mentorship.mentors.template.infrastructure;

import com.programmersonly.mentorship.mentors.template.application.CreatingMentorTemplate;
import com.programmersonly.mentorship.mentors.template.domain.MentorTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MentorTemplateConfiguration {

    @Bean
    CreatingMentorTemplate creatingMentorTemplate(MentorTemplates repository){
        return new CreatingMentorTemplate(repository);
    }

    @Bean
    MentorTemplates mentorTemplates(MentorTemplateEntityRepository repository){
        return new MentorTemplateDatabase(new DomainModelMapper(), repository);
    }
}
