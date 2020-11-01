package com.programmersonly.mentorship.commons.events.publisher;

import com.programmersonly.mentorship.commons.events.DomainEvent;
import com.programmersonly.mentorship.commons.events.DomainEvents;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;


@RequiredArgsConstructor
public class JustForwardDomainEventPublisher implements DomainEvents {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
