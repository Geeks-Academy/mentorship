package com.programmersonly.mentorship.offer;

import com.programmersonly.mentorship.offers.Attender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Attender")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttenderEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    private UUID attenderId;

    @Enumerated(EnumType.STRING)
    private Attender.Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="offer_id", nullable=false)
    private OfferEntity offer;
}
