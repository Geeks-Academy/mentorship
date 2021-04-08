package com.programmersonly.mentorship.direct.offer

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import com.programmersonly.mentorship.MentorshipApplication
import com.programmersonly.mentorship.offers.Attender
import com.programmersonly.mentorship.offers.Offer
import com.programmersonly.mentorship.offers.OfferState
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import spock.lang.Specification

import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.TEST_ATTENDER_ID
import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.TEST_OFFER_ID
import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.getCreateOfferRequest

@SpringBootTest(classes = MentorshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class])
class OfferControllerTest extends Specification {

    private static final String CREATE_PATH = "http://localhost:%d/offers/create"
    private static final String GET_OFFERS_PATCH = "http://localhost:%d/offers"
    private static final String ADD_ATTENDER_PATH = "http://localhost:%d/offers/%s/attender"
    private static final String FIND_OFFER_BY_ID_PATCH = "http://localhost:%d/offers/%s"

    @LocalServerPort
    private int port

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @ExpectedDatabase(value = "classpath:offer/create/expected-created-offer.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should create offer"() {
        given:
        CreateOfferRequest createOfferRequest = getCreateOfferRequest()

        when:
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CreateOfferRequest> request = new HttpEntity<>(createOfferRequest, headers)
        URI uri = new URI(String.format(CREATE_PATH, port))
        ResponseEntity response = restTemplate.postForEntity(uri, request, String.class)
        then:
        noExceptionThrown()
        response.getStatusCode() == HttpStatus.CREATED
    }

    @DatabaseSetup("classpath:offer/query/expected-offers-to-query.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return offers from repository"() {
        when:
        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH, port))
        ResponseEntity<Offer[]> response = restTemplate.getForEntity(uriGetOffers, Offer[].class)

        then:
        noExceptionThrown()
        response.body.length == 2
        Offer offer = response.getBody()[0]
        assert offer.getOwnerId() == OfferRequestProvider.USER_ID
        assert offer.getStartDate() == OfferRequestProvider.START_DATE
        assert offer.getEndDate() == OfferRequestProvider.END_DATE
    }

    @DatabaseSetup("classpath:offer/create/create-offer.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should add attender to the requestSet"() {

        when:
//      adding attender
        HttpHeaders headers = new HttpHeaders()
        HttpEntity<String> attenderRequest = new HttpEntity<>(TEST_ATTENDER_ID.toString(), headers)
        URI uriAddAttender = new URI(String.format(ADD_ATTENDER_PATH, port, TEST_OFFER_ID))
        ResponseEntity addAttenderResponse = restTemplate.postForEntity(uriAddAttender, attenderRequest, String.class)

//      Retrieve offer and check if attender is added
        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH, port))
        ResponseEntity<Offer[]> getOffers = restTemplate.getForEntity(uriGetOffers, Offer[].class)
        Offer offer = getOffers.getBody()[0]
        Set<Attender> requestSet = offer.getAttenders();

        then:
        noExceptionThrown()
        requestSet.getAt(0).getAttenderId().equals(TEST_ATTENDER_ID)
        addAttenderResponse.getStatusCode() == HttpStatus.CREATED
    }

    @DatabaseSetup("classpath:offer/query/expected-offers-to-query.xml")
    @DatabaseTearDown("classpath:clear-all.xml")
    def "should query offer by id"() {
        UUID offerId = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287451111")
        when:
        URI uriFindOfferById = new URI(String.format(FIND_OFFER_BY_ID_PATCH, port, offerId))
        ResponseEntity findOfferById = restTemplate.getForEntity(uriFindOfferById, Offer.class)
        Offer offer = findOfferById.getBody()

        then:
        noExceptionThrown()
        offer.getOfferId() == offerId
        offer.getStartDate().toString() == "2021-03-08T13:00"

        offer.getState() == OfferState.NEW

    }


    @DatabaseTearDown("classpath:clear-all.xml")
    def "should throw exception when cannot find offer"() {
        when: " Try to access not exiting offer"
        URI uriFindOfferById = new URI(String.format(FIND_OFFER_BY_ID_PATCH, port, UUID.randomUUID()))
        ResponseEntity<Offer> response =restTemplate.getForEntity(uriFindOfferById, Offer.class)

        then: "Should throw exception"
        response.getStatusCode() == HttpStatus.NOT_FOUND

    }


}


