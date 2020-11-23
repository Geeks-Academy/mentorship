package com.programmersonly.mentorship.mentors.account.shared;

import com.programmersonly.mentorship.commons.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreateMentorEvent implements DomainEvent {

    @NonNull private final UUID userId;

    @Override
    public UUID getEventId() {
        return UUID.randomUUID();
    }

    @Override
    public UUID getAggregateId() {
        return null;
    }

    @Override
    public Instant getWhen() {
        return Instant.now();
    }
}
