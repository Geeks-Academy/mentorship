package com.programmersonly.mentorship.direct.offer

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import com.programmersonly.mentorship.MentorshipApplication
import com.programmersonly.mentorship.offers.Offer
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

import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.getAddAttenderRequest
import static com.programmersonly.mentorship.direct.offer.OfferRequestProvider.getCreateOfferRequest;


@SpringBootTest(classes = MentorshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class])
class OfferControllerTest extends Specification {

    private static final String CREATE_PATH = "http://localhost:%d/offer/create"
    private static final String GET_OFFERS_PATCH = "http://localhost:%d/offer/offers"
    private static final String ADD_ATTENDER_PATH = "http://localhost:%d/offer/%s/attender"

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
        response.getStatusCode() == HttpStatus.CREATED
    }


    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should return offers from repository"(){
        given:
        CreateOfferRequest createOfferRequest = getCreateOfferRequest()

        when:
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CreateOfferRequest> request = new HttpEntity<>(createOfferRequest, headers)
        URI uriPostEntity = new URI(String.format(CREATE_PATH, port))
        restTemplate.postForEntity(uriPostEntity, request, String.class)


        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH,port))
        ResponseEntity<Offer[]>  response = restTemplate.getForEntity(uriGetOffers,Offer[].class)
        Offer offer = response.getBody()[0]


        then:
        offer.getOwnerId() == createOfferRequest.getOwnerId()
        offer.getStartDate() == createOfferRequest.getStartDate()
        offer.getEndDate() == createOfferRequest.getEndDate()
    }

    @DatabaseTearDown("classpath:clear-all.xml")
    def "Should add attender to the requestSet"(){
        given :
        CreateOfferRequest createOfferRequest = getCreateOfferRequest()
        AddAttenderRequest addAttenderRequest = getAddAttenderRequest()

        when :
//      Creating new offer for test
        HttpHeaders headers = new HttpHeaders()
        HttpEntity<CreateOfferRequest> request = new HttpEntity<>(createOfferRequest,headers)
        URI uriPostForEntity = new URI(String.format(CREATE_PATH,port))
        restTemplate.postForEntity(uriPostForEntity,request,String.class)

//      Get the id of the created offer
        URI uriGetOffers = new URI(String.format(GET_OFFERS_PATCH,port))
        ResponseEntity<Offer[]>  response = restTemplate.getForEntity(uriGetOffers,Offer[].class)
        UUID offerId = response.getBody()[0].getOfferId()


//      Testing adding attender
        headers = new HttpHeaders()
        HttpEntity<AddAttenderRequest> attenderRequest = new HttpEntity<>(addAttenderRequest,headers)
        URI uriAddAttender = new URI(String.format(ADD_ATTENDER_PATH,port,offerId))
        restTemplate.postForEntity(uriAddAttender,attenderRequest,String.class)

//      Retrieve offer once again and check if attedner is added
        response = restTemplate.getForEntity(uriGetOffers,Offer[].class)
        Offer offer = response.getBody()[0]
        println offer.getRequestSet()
        Set<UUID> requestSet = response.getBody()[0].getRequestSet()


        then:
        requestSet.contains(addAttenderRequest.getAttenderId())



    }

}
