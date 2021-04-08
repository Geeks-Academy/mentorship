package com.programmersonly.mentorship.direct.offer;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class OfferRequestProvider {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final UUID TEST_ATTENDER_ID = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287452222");
    public static final UUID TEST_OFFER_ID = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287450001");
    public static final UUID USER_ID = UUID.fromString("893a767b-3d02-4a10-b8d5-e36287451111");
    public static final LocalDateTime START_DATE= LocalDateTime.parse("2021-03-08 13:00", formatter);
    public static final LocalDateTime END_DATE = LocalDateTime.parse("2021-03-08 13:45", formatter);


    public static CreateOfferRequest getCreateOfferRequest(){
        return CreateOfferRequest.builder()
                .ownerId(USER_ID)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .build();
    }
}
