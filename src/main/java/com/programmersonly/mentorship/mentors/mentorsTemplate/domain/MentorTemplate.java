package com.programmersonly.mentorship.mentors.mentorsTemplate.domain;

import com.programmersonly.mentorship.commons.shared.UserId;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateConfirmationFailed;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateConfirmed;
import com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateCreated;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

import static com.programmersonly.mentorship.commons.events.EitherResult.announceFailure;
import static com.programmersonly.mentorship.commons.events.EitherResult.announceSuccess;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class MentorTemplate {

    private final MentorTemplateId mentorTemplateId;
    @NonNull private final UserId userId;
    @NonNull private final String description;
    private final MentorTemplateStatus status;

    // Really debatable
    public MentorTemplateCreated create() {
        return MentorTemplateCreated.now(this.userId, description);
    }

    public Either<MentorTemplateConfirmationFailed, MentorTemplateConfirmed> confirm(){
        if(status.equals(MentorTemplateStatus.CREATED)) {
            return announceSuccess(MentorTemplateConfirmed.now(mentorTemplateId));
        }
        return announceFailure(MentorTemplateConfirmationFailed.now(mentorTemplateId, "Wrong status"));
    }

    public static class Factory {
        public static MentorTemplate create(UUID userId, String description){
            return new MentorTemplate(null, new UserId(userId), description, MentorTemplateStatus.CREATED);
        }
    }

}

