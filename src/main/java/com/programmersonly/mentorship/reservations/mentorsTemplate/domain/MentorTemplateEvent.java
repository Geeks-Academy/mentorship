package com.programmersonly.mentorship.reservations.mentorsTemplate.domain;

import com.programmersonly.mentorship.commons.shared.UserId;
import com.programmersonly.mentorship.commons.events.DomainEvent;
import io.vavr.collection.List;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

public interface MentorTemplateEvent extends DomainEvent {

    default MentorTemplateId mentorTemplateId() {
        return new MentorTemplateId(getMentorTemplateId());
    }

    UUID getMentorTemplateId();

    default List<DomainEvent> normalize() {
        return List.of(this);
    }

    @Value
    class MentorTemplateCreated implements MentorTemplateEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant when;

        @NonNull UserId userId;
        @NonNull MentorTemplateStatus status;
        @NonNull String description;

        public static MentorTemplateCreated now(UserId userId, String description) {
            return new MentorTemplateCreated(Instant.now(), userId, MentorTemplateStatus.CREATED, description);
        }

        @Override
        public UUID getMentorTemplateId() {
           throw new IllegalArgumentException("Mentor creating...");
        }

        @Override
        public UUID getAggregateId() {
            throw new IllegalArgumentException("Mentor creating...");
        }
    }

    @Value
    class MentorTemplateCreateFailed implements MentorTemplateEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant when;

        @NonNull String reason;

        public static MentorTemplateCreateFailed now(String reason) {
            return new MentorTemplateCreateFailed(Instant.now(), reason);
        }

        @Override
        public UUID getMentorTemplateId() {
            throw new IllegalArgumentException("Mentor creating...");

        }

        @Override
        public UUID getAggregateId() {
            throw new IllegalArgumentException("Mentor creating...");
        }
    }

    @Value
    class MentorTemplateConfirmed {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant when;

        @NonNull MentorTemplateId mentorTemplateId;
        @NonNull MentorTemplateStatus status;
        public static MentorTemplateConfirmed now(MentorTemplateId mentorTemplateId) {
            return new MentorTemplateConfirmed(Instant.now(), mentorTemplateId, MentorTemplateStatus.CONFIRMED);
        }

    }
    @Value
    class MentorTemplateConfirmationFailed {

        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant when;

        @NonNull MentorTemplateId mentorTemplateId;
        @NonNull String reason;

        public static MentorTemplateConfirmationFailed now(MentorTemplateId mentorTemplateId, String reason) {
            return new MentorTemplateConfirmationFailed(Instant.now(), mentorTemplateId, reason);
        }
    }


}
