package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.CreateMeetingRequest;
import com.meetgenie.backend.dto.ApiResponse;
import com.meetgenie.backend.entity.Meeting;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.repository.MeetingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.meetgenie.backend.dto.MeetingResponse;
import java.util.ArrayList;
import java.util.List;
import com.meetgenie.backend.exception.MeetingNotFoundException;
import com.meetgenie.backend.repository.MeetingParticipantRepository;
import com.meetgenie.backend.entity.MeetingParticipant;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingParticipantRepository participantRepository;

    public MeetingService(
            MeetingRepository meetingRepository,
            MeetingParticipantRepository participantRepository) {

        this.meetingRepository = meetingRepository;
        this.participantRepository = participantRepository;
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

        MeetingParticipant participant = new MeetingParticipant();

        participant.setMeeting(meeting);
        participant.setUser(host);
        participant.setRole("HOST");
        participant.setJoinedAt(LocalDateTime.now());

        participantRepository.save(participant);

        return new ApiResponse(
                true,
                "Meeting created successfully."
        );
    }

    public List<MeetingResponse> getMyMeetings() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User host = (User) authentication.getPrincipal();

        List<Meeting> meetings = meetingRepository.findByHost(host);

        List<MeetingResponse> response = new ArrayList<>();

        for (Meeting meeting : meetings) {

            response.add(
                    new MeetingResponse(
                            meeting.getId(),
                            meeting.getTitle(),
                            meeting.getDescription(),
                            meeting.getMeetingCode(),
                            meeting.getStatus(),
                            meeting.getCreatedAt()
                    )
            );
        }

        return response;
    }

    public MeetingResponse getMeetingByCode(String meetingCode) {

        Meeting meeting = meetingRepository
                .findByMeetingCode(meetingCode)
                .orElseThrow(() ->
                        new MeetingNotFoundException("Meeting not found"));

        return new MeetingResponse(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getDescription(),
                meeting.getMeetingCode(),
                meeting.getStatus(),
                meeting.getCreatedAt()
        );
    }

}