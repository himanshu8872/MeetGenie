package com.meetgenie.backend.controller;

import com.meetgenie.backend.dto.*;
import com.meetgenie.backend.service.MeetingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ApiResponse createMeeting(
            @Valid @RequestBody CreateMeetingRequest request) {

        return meetingService.createMeeting(request);
    }

    @GetMapping
    public List<MeetingResponse> getMyMeetings() {
        return meetingService.getMyMeetings();
    }

    @GetMapping("/{meetingCode}")
    public MeetingResponse getMeetingByCode(
            @PathVariable String meetingCode) {

        return meetingService.getMeetingByCode(meetingCode);
    }

    @PostMapping("/join")
    public ApiResponse joinMeeting(
            @Valid @RequestBody JoinMeetingRequest request) {

        return meetingService.joinMeeting(request);
    }

    @PostMapping("/leave")
    public ApiResponse leaveMeeting(
            @Valid @RequestBody LeaveMeetingRequest request) {

        return meetingService.leaveMeeting(request);
    }

    @DeleteMapping
    @Transactional
    public ApiResponse deleteMeeting(
            @Valid @RequestBody DeleteMeetingRequest request) {

        return meetingService.deleteMeeting(request);
    }

    @PostMapping("/start")
    public ApiResponse startMeeting(
            @Valid @RequestBody StartMeetingRequest request) {

        return meetingService.startMeeting(request);
    }

    @PostMapping("/end")
    public ApiResponse endMeeting(
            @Valid @RequestBody EndMeetingRequest request) {

        return meetingService.endMeeting(request);
    }
}