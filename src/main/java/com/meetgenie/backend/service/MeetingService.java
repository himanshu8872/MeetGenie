package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.*;
import com.meetgenie.backend.entity.Meeting;
import com.meetgenie.backend.entity.MeetingParticipant;
import com.meetgenie.backend.entity.MeetingStatus;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.exception.*;
import com.meetgenie.backend.repository.MeetingParticipantRepository;
import com.meetgenie.backend.repository.MeetingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        User host = getCurrentUser();

        Meeting meeting = new Meeting();

        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setCreatedAt(LocalDateTime.now());
        meeting.setStatus(MeetingStatus.SCHEDULED);

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

        User host = getCurrentUser();

        List<Meeting> meetings = meetingRepository.findByHost(host);

        List<MeetingResponse> response = new ArrayList<>();

        for (Meeting meeting : meetings) {

            response.add(
                    new MeetingResponse(
                            meeting.getId(),
                            meeting.getTitle(),
                            meeting.getDescription(),
                            meeting.getMeetingCode(),
                            meeting.getStatus().name(),
                            meeting.getCreatedAt()
                    )
            );
        }

        return response;
    }

    public MeetingResponse getMeetingByCode(String meetingCode) {

        User currentUser = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(meetingCode);

        participantRepository
                .findByMeetingAndUser(meeting, currentUser)
                .orElseThrow(() ->
                new UnauthorizedMeetingAccessException(
                        "You are not a participant of this meeting."
                ));

        return new MeetingResponse(
                meeting.getId(),
                meeting.getTitle(),
                meeting.getDescription(),
                meeting.getMeetingCode(),
                meeting.getStatus().name(),
                meeting.getCreatedAt()
        );
    }

    public ApiResponse joinMeeting(JoinMeetingRequest request) {

        User currentUser = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(request.getMeetingCode());

        participantRepository
                .findByMeetingAndUser(meeting, currentUser)
                .ifPresent(participant -> {
                    throw new AlreadyJoinedMeetingException();
                });

        MeetingParticipant participant = new MeetingParticipant();

        participant.setMeeting(meeting);
        participant.setUser(currentUser);
        participant.setRole("PARTICIPANT");
        participant.setJoinedAt(LocalDateTime.now());

        participantRepository.save(participant);

        return new ApiResponse(true, "Joined meeting successfully.");
    }

    public ApiResponse leaveMeeting(LeaveMeetingRequest request) {

        User host = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(request.getMeetingCode());

        if (meeting.getHost().getId().equals(host.getId())) {
            throw new HostCannotLeaveMeetingException();
        }

        MeetingParticipant participant = participantRepository
                .findByMeetingAndUser(meeting, host)
                .orElseThrow(ParticipantNotFoundException::new);

        participantRepository.delete(participant);

        return new ApiResponse(true, "Left meeting successfully.");
    }

    public ApiResponse deleteMeeting(DeleteMeetingRequest request) {

        User host = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(request.getMeetingCode());

        validateMeetingHost(
                meeting,
                host,
                "Only the meeting host can delete the meeting."
        );

        participantRepository.deleteAllByMeeting(meeting);

        meetingRepository.delete(meeting);

        return new ApiResponse(
                true,
                "Meeting deleted successfully."
        );
    }

    public ApiResponse startMeeting(StartMeetingRequest request) {

        User host = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(request.getMeetingCode());

        validateMeetingHost(
                meeting,
                host,
                "Only the meeting host can start the meeting."
        );

        if (meeting.getStatus() == MeetingStatus.LIVE) {
            throw new MeetingAlreadyStartedException();
        }

        if (meeting.getStatus() == MeetingStatus.ENDED) {
            throw new MeetingAlreadyEndedException();
        }

        meeting.setStatus(MeetingStatus.LIVE);
        meeting.setStartedAt(LocalDateTime.now());

        meetingRepository.save(meeting);

        return new ApiResponse(
                true,
                "Meeting started successfully."
        );
    }

    public ApiResponse endMeeting(EndMeetingRequest request) {

        User host = getCurrentUser();

        Meeting meeting = getMeetingByCodeOrThrow(request.getMeetingCode());

        validateMeetingHost(
                meeting,
                host,
                "Only the meeting host can end the meeting."
        );

        if (meeting.getStatus() == MeetingStatus.SCHEDULED) {
            throw new MeetingNotStartedException();
        }

        if (meeting.getStatus() == MeetingStatus.ENDED) {
            throw new MeetingAlreadyEndedException();
        }

        meeting.setStatus(MeetingStatus.ENDED);
        meeting.setEndedAt(LocalDateTime.now());

        meetingRepository.save(meeting);

        return new ApiResponse(
                true,
                "Meeting ended successfully."
        );
    }

    private void validateMeetingHost(
            Meeting meeting,
            User currentUser,
            String message) {

        if (!meeting.getHost().getId().equals(currentUser.getId())) {
            throw new UnauthorizedMeetingAccessException(message);
        }
    }

    private Meeting getMeetingByCodeOrThrow(String meetingCode) {

        return meetingRepository
                .findByMeetingCode(meetingCode)
                .orElseThrow(() ->
                        new MeetingNotFoundException("Meeting not found"));
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

}