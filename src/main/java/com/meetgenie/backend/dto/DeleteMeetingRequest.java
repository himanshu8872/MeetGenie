package com.meetgenie.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMeetingRequest {

    @NotBlank(message = "Meeting code is required")
    private String meetingCode;
}