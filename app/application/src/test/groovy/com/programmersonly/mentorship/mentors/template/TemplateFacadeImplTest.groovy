package com.programmersonly.mentorship.mentors.template


import spock.lang.Specification

//ToDo przepisz testy
class TemplateFacadeImplTest extends Specification {
    /*private def templateRepository = new TestTemplateRepository()
    private def templateFacade = new TemplateFacade(templateRepository)
    def templateId = UUID.fromString("077a8544-2683-11eb-adc1-0242ac120002")

    def "should create template"() {
        given:
        def id = UUID.randomUUID()
        def email = "email@gmail.com"
        def fullName = "fullName"
        def templateRequest = [
                userId  : id,
                email   : email,
                fullName: fullName
        ] as CreateTemplateRequest

        when:
        templateFacade.create(templateRequest)

        then:
        templateRepository.findByUserId(id).isPresent()
        with(templateRepository.findByUserId(id).get()) {
            userId == id
            email == email
            fullName == fullName
            status == TemplateStatus.CREATED
        }
    }

    def "should confirm template"() {
        given:
        def templateRequest = [
                templateId: templateId,
        ] as ConfirmTemplateRequest

        and:
        createTestTemplate(TemplateStatus.CREATED)

        when:
        templateFacade.confirm(templateRequest)

        then:
        with(templateRepository.findById(templateId)) {
            isPresent()
            get().getStatus() == TemplateStatus.CONFIRMED
        }

    }

    def "should throw error when confirmed template doesn't exist"() {
        given:
        def templateRequest = [
                templateId: UUID.randomUUID(),
        ] as ConfirmTemplateRequest

        when:
        templateFacade.confirm(templateRequest)

        then:
        def ex = thrown(BusinessException)
        ex.getResponse().getBody().getErrorCode() == "MS-02"
    }

    def "should throw error when confirmed template is already confirmed"() {
        given:
        def templateRequest = [
                templateId: templateId,
        ] as ConfirmTemplateRequest

        and:
        createTestTemplate(TemplateStatus.CONFIRMED)

        when:
        templateFacade.confirm(templateRequest)

        then:
        def ex = thrown(BusinessException)
        ex.getResponse().getBody().getErrorCode() == "MS-01"
    }

    def "should remove template"() {
        given:
        def templateRequest = [
                templateId: templateId,
        ] as RemoveTemplateRequest

        and:
        createTestTemplate(TemplateStatus.CREATED)

        when:
        templateFacade.remove(templateRequest)

        then:
       templateRepository.findById(templateId).isEmpty()
    }

    def "should throw error when removed template doesn't exist"() {
        given:
        def templateRequest = [
                templateId: templateId,
        ] as RemoveTemplateRequest

        when:
        templateFacade.remove(templateRequest)

        then:
        def ex = thrown(BusinessException)
        ex.getResponse().getBody().getErrorCode() == "MS-02"
    }

    def "should throw error when removed template has different status than created"() {
        given:
        def templateRequest = [
                templateId: templateId,
        ] as RemoveTemplateRequest

        and:
        createTestTemplate(TemplateStatus.CONFIRMED)

        when:
        templateFacade.remove(templateRequest)

        then:
        def ex = thrown(BusinessException)
        ex.getResponse().getBody().getErrorCode() == "MS-02"
    }

    private def createTestTemplate(TemplateStatus status) {
        templateRepository.save(
                [id: templateId, status: status] as TemplateEntity)
    }*/
}
