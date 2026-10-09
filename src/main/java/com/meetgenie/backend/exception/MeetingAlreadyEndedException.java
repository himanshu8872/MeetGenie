package com.meetgenie.backend.exception;

public class MeetingAlreadyEndedException extends RuntimeException {

    public MeetingAlreadyEndedException() {
        super("Meeting has already ended.");
    }
}