package com.meetgenie.backend.controller;

import com.meetgenie.backend.dto.ApiResponse;
import com.meetgenie.backend.dto.CreateMeetingRequest;
import com.meetgenie.backend.service.MeetingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.meetgenie.backend.dto.MeetingResponse;
import com.meetgenie.backend.dto.JoinMeetingRequest;
import com.meetgenie.backend.dto.LeaveMeetingRequest;

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

}