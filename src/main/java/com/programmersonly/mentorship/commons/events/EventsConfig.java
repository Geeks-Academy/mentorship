package com.programmersonly.mentorship.commons.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventsConfig {

    @Bean
    EventPublisher springEventPublisher(ApplicationEventPublisher publisher){
        return new SpringEventPublisher(publisher);
    }
}
