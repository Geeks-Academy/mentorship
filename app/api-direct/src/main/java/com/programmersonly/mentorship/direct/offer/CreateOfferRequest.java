package com.programmersonly.mentorship.direct.offer;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class CreateOfferRequest {
    //  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private UUID ownerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


/*    public CreateOfferRequest(UUID ownerId, String startDate, String endDate) {
        this.ownerId = ownerId;
        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
    }*/
}

