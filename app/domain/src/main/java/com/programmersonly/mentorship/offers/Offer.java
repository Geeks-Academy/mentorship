package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.offers.OfferState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class Offer {

    private UUID offerId;
    private UUID ownerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<UUID> requestSet;
    private UUID attenderId;
    private CanceledBy canceledBy;
    private LocalDateTime gradeDate;
    private OfferState state;
    private int gradeValue;
}
