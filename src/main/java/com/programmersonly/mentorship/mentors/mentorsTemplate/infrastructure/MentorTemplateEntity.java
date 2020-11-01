package com.programmersonly.mentorship.mentors.mentorsTemplate.infrastructure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Table(name = "mentor_template")
public class MentorTemplateEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String description;

    private String status;

    private UUID userId;

    public MentorTemplateEntity(String description, String status, UUID userId) {
        this.description = description;
        this.status = status;
        this.userId = userId;
    }
}
