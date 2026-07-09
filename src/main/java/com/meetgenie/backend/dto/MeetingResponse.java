package com.meetgenie.backend.dto;

import java.time.LocalDateTime;

public class MeetingResponse {

    private Long id;
    private String title;
    private String description;
    private String meetingCode;
    private String status;
    private LocalDateTime createdAt;

    public MeetingResponse() {
    }

    public MeetingResponse(
            Long id,
            String title,
            String description,
            String meetingCode,
            String status,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.meetingCode = meetingCode;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMeetingCode() {
        return meetingCode;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}