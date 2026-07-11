package com.meetgenie.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParticipantResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
}