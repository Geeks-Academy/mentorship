package com.programmersonly.mentorship.direct.offer;


import com.programmersonly.mentorship.offers.Offer;
import com.programmersonly.mentorship.offers.OfferService;
import com.programmersonly.mentorship.offers.dto.AddAttenderDto;
import com.programmersonly.mentorship.offers.dto.CreateOfferDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/offer")
public class OfferController {

    private OfferService offerService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMentorTemplate(@RequestBody CreateOfferRequest request) {
        CreateOfferDto offerDto = CreateOfferDto.builder()
                .ownerId(request.getOwnerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        System.out.println("controller");
        offerService.create(offerDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/offers")
    public ResponseEntity<Collection<Offer>> offers() {

        Collection<Offer> list = offerService.offers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/{offerId}/attender")
    public ResponseEntity<Void> addAttender(@PathVariable UUID offerId, @RequestBody AddAttenderRequest request) {
        AddAttenderDto addAttenderDto =
                AddAttenderDto.builder()
                        .attenderId(request.getAttenderId())
                        .build();
        offerService.addAttender(offerId, addAttenderDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<Offer> getOffer(@PathVariable UUID offerId){
        Offer offer = offerService.getOffer(offerId);
        return new ResponseEntity(offer, HttpStatus.OK);
    }
}
