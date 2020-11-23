package com.programmersonly.mentorship.mentors.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface MentorEntityRepository extends JpaRepository<MentorEntity, UUID> {
}
