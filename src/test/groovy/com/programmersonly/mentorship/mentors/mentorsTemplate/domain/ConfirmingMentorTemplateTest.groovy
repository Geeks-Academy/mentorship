package com.programmersonly.mentorship.mentors.mentorsTemplate.domain

import io.vavr.control.Either
import spock.lang.Specification

import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateFixture.confirmedMentorTemplate
import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateFixture.createdMentorTemplate
import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateConfirmed
import static com.programmersonly.mentorship.mentors.mentorsTemplate.domain.MentorTemplateEvent.MentorTemplateConfirmationFailed

class ConfirmingMentorTemplateTest extends Specification {

    def 'user should be able to confirm mentor template'() {
        given:
            MentorTemplate template = createdMentorTemplate()
        when:
            Either<MentorTemplateConfirmationFailed, MentorTemplateConfirmed> result = template.confirm()
        then:
            result.isRight()
            result.get().with {
                it.status == MentorTemplateStatus.CONFIRMED
            }
    }

    def 'user cannot confirm mentor template already confirmed / rejected / accepted'() {
        given:
            MentorTemplate template = confirmedMentorTemplate()
        when:
            Either<MentorTemplateConfirmationFailed, MentorTemplateConfirmed> result = template.confirm()
        then:
            result.isLeft()
    }

}
