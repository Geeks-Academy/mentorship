package com.programmersonly.mentorship.commons.events;

public interface EventPublisher {
    void publish(DomainEvent event);
}
