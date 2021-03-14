package com.programmersonly.mentorship.offers;

import com.programmersonly.mentorship.exception.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.Duration;
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

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", ownerId=" + ownerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", requestSet=" + requestSet +
                ", attenderId=" + attenderId +
                ", canceledBy=" + canceledBy +
                ", gradeDate=" + gradeDate +
                ", state=" + state +
                ", gradeValue=" + gradeValue +
                '}';
    }

    private void transitionToState(OfferState state) {
        this.state = state;
    }

    void addUserToRequestSet(UUID userID) {
        if (state == OfferState.NEW_OFFER) {
            requestSet.add(userID);
        } else {
            throw new AddAttenderException();
        }


    }


    void confirmAttender(UUID attenderId) {
        if (state != OfferState.NEW_OFFER) {
            throw new ConfirmAttenderException();
        }


        if (!requestSet.contains(attenderId)) {
            throw new AttenderNotInRequestSetException();
        }
        this.attenderId = attenderId;

        transitionToState(OfferState.CONSULTATION);

    }

    void cancel(UUID canceledById) {

        if (state == OfferState.CONSULTATION) {
            determineByWhomWasCanceled(canceledById);
            transitionToState(OfferState.CANCELLED_CONSULTATION);

        } else if (state == OfferState.NEW_OFFER) {
            determineByWhomWasCanceled(canceledById);
            transitionToState(OfferState.CANCELLED_OFFER);

        } else {
            throw new OfferCancellationException(ErrorCode.MS15);
        }

    }

    void grade(int gradeValue, UUID attenderId) {


        checkIfSuitableForGrading(attenderId);
        this.gradeValue = gradeValue;
        transitionToState(OfferState.FINISHED_GRADED);

    }

    private void checkIfSuitableForGrading(UUID attenderId) {
        if (state != OfferState.FINISHED_UNGRADED)
           throw new GradeOfferException(ErrorCode.MS17);

        if (!this.attenderId.equals(attenderId))
            throw new GradeOfferException(ErrorCode.MS18);

    }


    OfferState finish() {
        if (state == OfferState.CONSULTATION && compareDateTimeToMinutes(startDate, LocalDateTime.now()) <= 0) {
            transitionToState(OfferState.FINISHED_UNGRADED);
        }

        return state;
    }

    OfferState grade() {
        if (state == OfferState.FINISHED_UNGRADED && compareDateTimeToMinutes(gradeDate, LocalDateTime.now()) <= 0) {
            transitionToState(OfferState.FINISHED_GRADED);
        }
        return state;
    }

    private long compareDateTimeToMinutes(LocalDateTime dateTime, LocalDateTime now) {
        Duration duration = Duration.between(now, dateTime);
        return duration.toMinutes();
    }

    private void determineByWhomWasCanceled(UUID canceledbyid){

        if ( canceledbyid.equals(ownerId)){
                canceledBy = CanceledBy.Mentor;
        } else if (canceledbyid.equals(attenderId)){
            canceledBy = CanceledBy.Stunent;
        } else{
            throw new OfferCancellationException(ErrorCode.MS16);
        }

    }
}
