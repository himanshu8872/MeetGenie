package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.CreateMeetingRequest;
import com.meetgenie.backend.dto.ApiResponse;
import com.meetgenie.backend.entity.Meeting;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.repository.MeetingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public ApiResponse createMeeting(CreateMeetingRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User host = (User) authentication.getPrincipal();

        Meeting meeting = new Meeting();

        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setCreatedAt(LocalDateTime.now());
        meeting.setStatus("ACTIVE");

        String meetingCode = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        meeting.setMeetingCode(meetingCode);
        meeting.setHost(host);

        meetingRepository.save(meeting);

        return new ApiResponse(
                true,
                "Meeting created successfully."
        );
    }

}