package com.programmersonly.mentorship.mentors.account;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mentors")
class MentorEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Type(type = "uuid-char")
    private UUID userId;

    private MentorStatus status;

    private Instant modified;
    private Instant created;

    private String firstName;
    private String lastName;
    private String jobTitle;
    private String companyName;
    private String email;
    private String phone;
    private String bio;
    private String localization;
    private Long cost;
    private Integer seniority;
    private Category category;

    @OneToMany
    private Set<SocialLinkEntity> socialLinks;

    @ManyToMany
    private Set<SkillEntity> skills;

    public MentorEntity(UUID userId) {
        this.userId = userId;
    }
}
