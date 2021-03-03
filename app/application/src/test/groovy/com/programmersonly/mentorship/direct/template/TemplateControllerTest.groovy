package com.programmersonly.mentorship.direct.template

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import com.programmersonly.mentorship.MentorshipApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import spock.lang.Specification

@SpringBootTest(classes = MentorshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class])
class TemplateControllerTest extends Specification {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @DatabaseSetup("classpath:template/confirm/unconfirmed-template.xml")
    @ExpectedDatabase("classpath:template/confirm/expected-confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should confirm template"() {
        when:
        ConfirmTemplateRequest confirmTemplateRequest = new ConfirmTemplateRequest()
        confirmTemplateRequest.templateId = UUID.fromString("893a767b-3d02-4a10-b8d5-e3628745eafa");
        ResponseEntity<String> response = restTemplate.exchange(String.format("http://localhost:%d/template/confirm", port), HttpMethod.PUT, new HttpEntity<ConfirmTemplateRequest>(confirmTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.OK
    }

    @DatabaseSetup("classpath:template/confirm/confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return CONFLICT while template confirmation"() {
        when:
        ConfirmTemplateRequest confirmTemplateRequest = new ConfirmTemplateRequest()
        confirmTemplateRequest.templateId = UUID.fromString("893a767b-3d02-4a10-b8d5-e3628745eafa");
        ResponseEntity<String> response = restTemplate.exchange(String.format("http://localhost:%d/template/confirm", port), HttpMethod.PUT, new HttpEntity<ConfirmTemplateRequest>(confirmTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.CONFLICT;
    }

    @ExpectedDatabase(value = "classpath:template/create/expected-created-template.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should create template"() {
        when:
        CreateTemplateRequest createTemplateRequest = new CreateTemplateRequest()
        createTemplateRequest.userId = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287451111");
        createTemplateRequest.email = "Phillip@test.pl";
        createTemplateRequest.fullName = "Phillip";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CreateTemplateRequest> request = new HttpEntity<>(createTemplateRequest, headers);
        ResponseEntity response = restTemplate.postForEntity(new URI("http://localhost:" + port + "/template/create"), request, String.class)

        then:
        response.getStatusCode() == HttpStatus.CREATED
    }

    @DatabaseSetup("classpath:template/delete/unconfirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should delete template"() {
        when:
        RemoveTemplateRequest removeTemplateRequest = new RemoveTemplateRequest()
        removeTemplateRequest.templateId = UUID.fromString("893a767b-3d02-4a10-b8d5-e3628745eafa");
        ResponseEntity<String> response = restTemplate.exchange(String.format("http://localhost:%d/template/delete", port), HttpMethod.DELETE, new HttpEntity<RemoveTemplateRequest>(removeTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.OK;
    }

    @DatabaseSetup("classpath:template/delete/confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return NOT_FOUND while deleting template"() {
        when:
        RemoveTemplateRequest removeTemplateRequest = new RemoveTemplateRequest()
        removeTemplateRequest.templateId = UUID.fromString("893a767b-3d02-4a10-b8d5-e3628745eafa");
        ResponseEntity<String> response = restTemplate.exchange(String.format("http://localhost:%d/template/delete", port), HttpMethod.DELETE, new HttpEntity<RemoveTemplateRequest>(removeTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

}
