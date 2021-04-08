package com.programmersonly.mentorship.direct.offer;


import com.programmersonly.mentorship.offers.Offer;
import com.programmersonly.mentorship.offers.OfferService;
import com.programmersonly.mentorship.offers.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/offers")
public class OfferController {

    private OfferService offerService;

    @PostMapping("/create")
    public ResponseEntity<Void> createOffer(@RequestBody CreateOfferRequest request) {
        CreateOfferDto offerDto = CreateOfferDto.builder()
                .ownerId(request.getOwnerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        offerService.create(offerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Collection<Offer>> getOffers() {
        Collection<Offer> list = offerService.getOffers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/{offerId}/attender")
    public ResponseEntity<Void> addAttender(@PathVariable UUID offerId, @RequestBody String attenderId) {
        AddAttenderDto addAttenderDto = AddAttenderDto.builder()
                        .attenderId(UUID.fromString(attenderId))
                        .offerId(offerId)
                        .build();
        offerService.addAttender(addAttenderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<Offer> getOffer(@PathVariable UUID offerId) {
        Offer offer = offerService.getOffer(offerId);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("/{offerId}/cancel")
    public ResponseEntity<Void> cancelOffer(@PathVariable UUID offerId, @RequestBody String attenderId) {
        CancelOfferDto dto = CancelOfferDto.builder()
                .cancelBy(UUID.fromString(attenderId))
                .offerId(offerId)
                .build();
        offerService.cancel(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{offerId}/attender/confirm")
    public ResponseEntity<Void> cancel(@PathVariable UUID offerId, @RequestBody String attenderId){
        ConfirmAttenderDto dto = ConfirmAttenderDto.builder()
                .attenderId(UUID.fromString(attenderId))
                .offerId(offerId)
                .build();
        offerService.confirmAttender(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{offerId}/gradeOffer")
    public ResponseEntity<Void> grade(@PathVariable UUID offerId, GradeOfferRequest request){
        GradeOfferDto dto = GradeOfferDto.builder()
                .gradeValue(request.getGradeValue())
                .attenderID(request.getAttenderId())
                .build();
        offerService.gradeOffer(offerId,dto);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
