package com.programmersonly.mentorship.offers.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddAttenderDto {
    private UUID attenderId;

    private UUID offerId;

}
