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
    private UUID ownerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

