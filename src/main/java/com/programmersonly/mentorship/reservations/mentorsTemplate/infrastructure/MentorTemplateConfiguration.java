package com.programmersonly.mentorship.reservations.mentorsTemplate.infrastructure;

import com.programmersonly.mentorship.reservations.mentorsTemplate.application.CreatingMentorTemplate;
import com.programmersonly.mentorship.reservations.mentorsTemplate.domain.MentorTemplates;
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
