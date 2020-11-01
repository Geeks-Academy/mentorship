package com.programmersonly.mentorship.commons.events.publisher;

import com.programmersonly.mentorship.commons.events.DomainEvents;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsConfig {

    @Bean
    DomainEvents domainEvents(ApplicationEventPublisher publisher) {
        return new JustForwardDomainEventPublisher(publisher);
    }
}
