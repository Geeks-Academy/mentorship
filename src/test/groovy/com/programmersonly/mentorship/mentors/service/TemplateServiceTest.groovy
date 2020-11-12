package com.programmersonly.mentorship.mentors.service

import com.programmersonly.mentorship.mentors.controller.request.ConfirmTemplateRequest
import com.programmersonly.mentorship.mentors.controller.request.CreateTemplateRequest
import com.programmersonly.mentorship.mentors.controller.request.RemoveTemplateRequest
import com.programmersonly.mentorship.mentors.domain.Template
import com.programmersonly.mentorship.mentors.domain.TemplateId
import com.programmersonly.mentorship.mentors.entity.TemplateStatus
import com.programmersonly.mentorship.mentors.repository.TemplateRepository
import com.programmersonly.mentorship.mentors.service.exception.EmailAlreadyUsed
import com.programmersonly.mentorship.mentors.service.exception.ForbiddenTemplateStatusToDelete
import com.programmersonly.mentorship.mentors.service.exception.TemplateAlreadyConfirmed
import com.programmersonly.mentorship.mentors.service.exception.TemplateNotFound
import com.programmersonly.mentorship.mentors.service.mapper.TemplateMapper
import spock.lang.Specification

class TemplateServiceTest extends Specification {

    private def repository = Mock(TemplateRepository)
    private def mapper = new TemplateMapper()
    private def templateService = new TemplateService(repository, mapper)


    def "should create template"() {
        given:
        def email = "email@gmail.com"
        def templateRequest = [
            userId: "userId",
            email: email,
            fullName: "fullName"
        ] as CreateTemplateRequest


        when:
        templateService.create(templateRequest)

        then:
        1 * repository.existsByEmail(email) >> false
        1 * repository.save(Template.builder()
                .email(email)
                .fullName("fullName")
                .status(TemplateStatus.CREATED)
                .build())
    }

    def "should fail to template when email is already used"() {
        given:
        def email = "email@gmail.com"
        def templateRequest = [
                email: email,
        ] as CreateTemplateRequest

        when:
        templateService.create(templateRequest)

        then:
        1 * repository.existsByEmail(email) >> true
        0 * repository.save(_)
        thrown(EmailAlreadyUsed)
    }

    def "should confirm template"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as ConfirmTemplateRequest

        def foundTemplate = Template.builder()
                .status(TemplateStatus.CREATED)
                .build()

        when:
        templateService.confirm(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.of(foundTemplate)
        1 * repository.save(foundTemplate)
    }

    def "should throw error when confirmed template doesn't exist"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as ConfirmTemplateRequest

        when:
        templateService.confirm(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.empty()
        0 * repository.save(_)
        thrown(TemplateNotFound)
    }

    def "should throw error when confirmed template is already confirmed"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as ConfirmTemplateRequest

        def foundTemplate = Template.builder()
                .status(TemplateStatus.CONFIRMED)
                .build()

        when:
        templateService.confirm(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.of(foundTemplate)
        0 * repository.save(_)
        thrown(TemplateAlreadyConfirmed)
    }

    def "should remove template"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as RemoveTemplateRequest


        when:
        templateService.remove(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.of(Template.builder()
                .status(TemplateStatus.CREATED)
                .build())
        1 * repository.deleteById(templateId)
    }

    def "should throw error when removed template doesn't exist"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as RemoveTemplateRequest


        when:
        templateService.remove(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.empty()
        0 * repository.deleteById(templateId)
        thrown(TemplateNotFound)
    }

    def "should throw error when removed template has different status than created"() {
        given:
        def templateId = TemplateId.create()
        def templateRequest = [
                templateId: templateId.getValue().toString(),
        ] as RemoveTemplateRequest


        when:
        templateService.remove(templateRequest)

        then:
        1 * repository.findById(templateId) >> Optional.of(Template.builder()
                .status(TemplateStatus.CONFIRMED)
                .build())
        0 * repository.deleteById(templateId)
        thrown(ForbiddenTemplateStatusToDelete)
    }
}
