package com.programmersonly.mentorship.reservations.mentorsTemplate.domain

import spock.lang.Specification

import static com.programmersonly.mentorship.reservations.mentorsTemplate.domain.MentorTemplateFixture.notExistingTemplate

class CreatingMentorTemplateTest extends Specification {

    def 'user should be able to create mentor template'() {
        given:
            MentorTemplate template = notExistingTemplate()
        when:
            MentorTemplateEvent.MentorTemplateCreated created =  template.create()
        then:
            created.status == MentorTemplateStatus.CREATED
    }

}
