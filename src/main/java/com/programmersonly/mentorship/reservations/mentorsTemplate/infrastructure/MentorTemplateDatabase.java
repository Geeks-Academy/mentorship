package com.programmersonly.mentorship.reservations.mentorsTemplate.infrastructure;

import com.programmersonly.mentorship.commons.shared.UserId;
import com.programmersonly.mentorship.reservations.mentorsTemplate.domain.*;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import static com.programmersonly.mentorship.reservations.mentorsTemplate.domain.MentorTemplateEvent.*;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MentorTemplateDatabase implements MentorTemplates {

    private final DomainModelMapper domainModelMapper;
    private final MentorTemplateEntityRepository mentorTemplateEntityRepository;


    @Override
    public Option<MentorTemplate> findBy(MentorTemplateId id) {
        return mentorTemplateEntityRepository
                .findById(id.getMentorTemplateId())
                .map(domainModelMapper::map)
                .map(Option::of)
                .orElseGet(Option::none);
    }

    @Override
    public Option<MentorTemplate> findBy(UserId userId) {
        return mentorTemplateEntityRepository
                .findByUserId(userId.getUserId())
                .map(domainModelMapper::map)
                .map(Option::of)
                .orElseGet(Option::none);
    }

    @Override
    public MentorTemplate publish(MentorTemplateEvent domainEvent) {
        return Match(domainEvent).of(
                Case($(instanceOf(MentorTemplateCreated.class)), this::createNewMentorTemplate)
        );
    }

    private MentorTemplate createNewMentorTemplate(MentorTemplateCreated domainEvent) {
        MentorTemplateEntity entity = mentorTemplateEntityRepository
                .save(new MentorTemplateEntity(domainEvent.getDescription(), domainEvent.getStatus().name(), domainEvent.getUserId().getUserId()));
        return domainModelMapper.map(entity);
    }
}

interface MentorTemplateEntityRepository extends JpaRepository<MentorTemplateEntity, UUID> {
    Optional<MentorTemplateEntity> findByUserId(UUID userId);

}

class DomainModelMapper {

    MentorTemplate map(MentorTemplateEntity entity) {
        return MentorTemplate.Factory.create(entity.getUserId(), entity.getDescription());
    }
}

