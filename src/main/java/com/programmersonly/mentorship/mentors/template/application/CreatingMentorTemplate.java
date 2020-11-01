package com.programmersonly.mentorship.mentors.template.application;

import com.programmersonly.mentorship.commons.commands.Result;
import com.programmersonly.mentorship.mentors.template.domain.MentorTemplate;
import com.programmersonly.mentorship.mentors.template.domain.MentorTemplates;
import com.programmersonly.mentorship.commons.shared.UserId;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static com.programmersonly.mentorship.commons.commands.Result.Rejection;
import static com.programmersonly.mentorship.commons.commands.Result.Success;
import static com.programmersonly.mentorship.mentors.template.domain.MentorTemplateEvent.MentorTemplateCreated;

@AllArgsConstructor
@Slf4j
public class CreatingMentorTemplate {

    private final MentorTemplates mentorTemplateRepository;

    public Try<Result> create(@NonNull CreateMentorCommand command) {
        return Try.of(() -> {
            // Refactor, maybe better throw exception and catch?
            Option<MentorTemplate> exists = mentorTemplateRepository.findBy(new UserId(command.getUserId()));
            if(exists.isDefined()) {
                return Rejection;
            }
            MentorTemplate mentorTemplate = MentorTemplate.Factory.create(command.getUserId(), command.getDescription());
            MentorTemplateCreated result = mentorTemplate.create();
            this.publishEvents(result);
            return Success;
        });
    }


    private Result publishEvents(MentorTemplateCreated templateCreated) {
        mentorTemplateRepository.publish(templateCreated);
        return Success;
    }

}
