package com.meetgenie.backend.repository;

import com.meetgenie.backend.entity.Meeting;
import com.meetgenie.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByHost(User host);

    Optional<Meeting> findByMeetingCode(String meetingCode);



}