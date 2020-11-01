package com.programmersonly.mentorship.mentors.mentorsTemplate.application;

import com.programmersonly.mentorship.commons.commands.Result;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplate;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateCreateFailed;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplates;
import com.programmersonly.mentorship.commons.shared.UserId;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static com.programmersonly.mentorship.commons.commands.Result.Rejection;
import static com.programmersonly.mentorship.commons.commands.Result.Success;
import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateCreated;

@AllArgsConstructor
@Slf4j
public class CreatingMentorTemplate {

    private final MentorTemplates mentorTemplateRepository;

    public Try<Result> create(@NonNull CreateMentorCommand command) {
        return Try.of(() -> {
            // Refactor, maybe better throw exception and catch?
            boolean isEmpty = mentorTemplateRepository.findBy(new UserId(command.getUserId())).isEmpty();
            if(!isEmpty) {
                this.publishEvents(MentorTemplateCreateFailed.now("User already created template."));
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

    private Result publishEvents(MentorTemplateCreateFailed createFailed) {
        mentorTemplateRepository.publish(createFailed);
        return Rejection;
    }

}
