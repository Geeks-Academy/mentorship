package com.programmersonly.mentorship.direct.offer;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GradeOfferRequest {
    private int gradeValue;
    private UUID attenderId;
}
