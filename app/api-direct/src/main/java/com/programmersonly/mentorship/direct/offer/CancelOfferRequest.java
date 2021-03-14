package com.programmersonly.mentorship.direct.offer;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CancelOfferRequest {

    private UUID canceledById;
}
