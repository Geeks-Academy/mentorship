package com.programmersonly.mentorship.direct.offer

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import com.programmersonly.mentorship.MentorshipApplication
import com.programmersonly.mentorship.exception.CannotFindOfferException
import com.programmersonly.mentorship.offers.Offer
import com.programmersonly.mentorship.offers.OfferState
import org.codehaus.groovy.runtime.metaclass.MetaMethodIndex
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.xml.FlatXmlDataSet
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

import java.time.LocalDateTime

import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.getAddAttenderRequest
import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.getCreateOfferRequest;


@SpringBootTest(classes = MentorshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class])
class OfferControllerTest extends Specification {

    private static final String CREATE_PATH = "http://localhost:%d/offer/create"
    private static final String GET_OFFERS_PATCH = "http://localhost:%d/offer/offers"
    private static final String ADD_ATTENDER_PATH = "http://localhost:%d/offer/%s/attender"
    private static final String FIND_OFFER_BY_ID_PATCH = "http://localhost:%d/offer/%s"

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
        given:
        CreateOfferRequest createOfferRequest = getCreateOfferRequest()

        when:

        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH, port))
        ResponseEntity<Offer[]> response = restTemplate.getForEntity(uriGetOffers, Offer[].class)
        Offer offer = response.getBody()[0]


        then:
        noExceptionThrown()
        response.body.length == 2
//        offer.getOwnerId() == createOfferRequest.getOwnerId()
//        offer.getStartDate() == createOfferRequest.getStartDate()
//        offer.getEndDate() == createOfferRequest.getEndDate()
    }

    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should add attender to the requestSet"() {
        given:
        CreateOfferRequest createOfferRequest = getCreateOfferRequest()
        AddAttenderRequest addAttenderRequest = getAddAttenderRequest()

        when:
//      Creating new offer for test
        HttpHeaders headers = new HttpHeaders()
        HttpEntity<CreateOfferRequest> request = new HttpEntity<>(createOfferRequest, headers)
        URI uriPostForEntity = new URI(String.format(CREATE_PATH, port))
        restTemplate.postForEntity(uriPostForEntity, request, String.class)

//      Get the id of the created offer
        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH, port))
        ResponseEntity<Offer[]> response = restTemplate.getForEntity(uriGetOffers, Offer[].class)
        UUID offerId = response.getBody()[0].getOfferId()


//      adding attender
        headers = new HttpHeaders()
        HttpEntity<AddAttenderRequest> attenderRequest = new HttpEntity<>(addAttenderRequest, headers)
        URI uriAddAttender = new URI(String.format(ADD_ATTENDER_PATH, port, offerId))
        ResponseEntity addAttenderResponse = restTemplate.postForEntity(uriAddAttender, attenderRequest, String.class)

//      Retrieve offer once again and check if attender is added
        ResponseEntity<Offer[]> getOffers = restTemplate.getForEntity(uriGetOffers, Offer[].class)
        Offer offer = getOffers.getBody()[0]
        Set<UUID> requestSet = offer.getRequestSet();


        then:
        noExceptionThrown()
        requestSet.contains(addAttenderRequest.getAttenderId())
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

        offer.getState() == OfferState.NEW_OFFER

    }


    @DatabaseTearDown("classpath:clear-all.xml")
    def "should throw exception when cannot find offer"() {
        when: " Try to access not exiting offer"
        URI uriFindOfferById = new URI(String.format(FIND_OFFER_BY_ID_PATCH, port, UUID.randomUUID()))
        restTemplate.getForEntity(uriFindOfferById, Offer.class)

        then: "Should throw exception"
        thrown NoSuchElementException

    }


}


