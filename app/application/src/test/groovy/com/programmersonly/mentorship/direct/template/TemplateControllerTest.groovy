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

import static com.programmersonly.mentorship.direct.template.TemplateRequestProvider.getConfirmTemplateRequest
import static com.programmersonly.mentorship.direct.template.TemplateRequestProvider.getCreateTemplateRequest
import static com.programmersonly.mentorship.direct.template.TemplateRequestProvider.getRemoveTemplateRequest

@SpringBootTest(classes = MentorshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class])
class TemplateControllerTest extends Specification {

    private static final String CONFIRM_PATH = "http://localhost:%d/template/confirm";
    private static final String CREATE_PATH = "http://localhost:%d/template/create";
    private static final String DELETE_PATH = "http://localhost:%d/template/delete";

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @DatabaseSetup("classpath:template/confirm/unconfirmed-template.xml")
    @ExpectedDatabase("classpath:template/confirm/expected-confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should confirm template"() {
        given:
        ConfirmTemplateRequest confirmTemplateRequest = getConfirmTemplateRequest()

        when:
        ResponseEntity<String> response = restTemplate.exchange(String.format(CONFIRM_PATH, port), HttpMethod.PUT, new HttpEntity<ConfirmTemplateRequest>(confirmTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.OK
    }

    @DatabaseSetup("classpath:template/confirm/confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return CONFLICT while template confirmation"() {
        given:
        ConfirmTemplateRequest confirmTemplateRequest = getConfirmTemplateRequest()

        when:
        ResponseEntity<String> response = restTemplate.exchange(String.format(CONFIRM_PATH, port), HttpMethod.PUT, new HttpEntity<ConfirmTemplateRequest>(confirmTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.CONFLICT;
    }

    @ExpectedDatabase(value = "classpath:template/create/expected-created-template.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should create template"() {
        given:
        CreateTemplateRequest createTemplateRequest = getCreateTemplateRequest()

        when:
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CreateTemplateRequest> request = new HttpEntity<>(createTemplateRequest, headers);
        URI uri = new URI(String.format(CREATE_PATH, port))
        ResponseEntity response = restTemplate.postForEntity(uri, request, String.class)

        then:
        response.getStatusCode() == HttpStatus.CREATED
    }

    @DatabaseSetup("classpath:template/delete/unconfirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should delete template"() {
        given:
        RemoveTemplateRequest removeTemplateRequest = getRemoveTemplateRequest()

        when:
        ResponseEntity<String> response = restTemplate.exchange(String.format(DELETE_PATH, port), HttpMethod.DELETE, new HttpEntity<RemoveTemplateRequest>(removeTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.OK;
    }

    @DatabaseSetup("classpath:template/delete/confirmed-template.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return NOT_FOUND while deleting template"() {
        given:
        RemoveTemplateRequest removeTemplateRequest = getRemoveTemplateRequest()

        when:
        ResponseEntity<String> response = restTemplate.exchange(String.format(DELETE_PATH, port), HttpMethod.DELETE, new HttpEntity<RemoveTemplateRequest>(removeTemplateRequest), String.class);

        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

}
