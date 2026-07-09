package com.meetgenie.backend.repository;

import com.meetgenie.backend.entity.Meeting;
import com.meetgenie.backend.entity.MeetingParticipant;
import com.meetgenie.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingParticipantRepository
        extends JpaRepository<MeetingParticipant, Long> {

    List<MeetingParticipant> findByMeeting(Meeting meeting);

    List<MeetingParticipant> findByUser(User user);

    Optional<MeetingParticipant> findByMeetingAndUser(
            Meeting meeting,
            User user
    );
}