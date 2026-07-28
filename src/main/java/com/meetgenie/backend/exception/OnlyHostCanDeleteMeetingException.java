package com.meetgenie.backend.exception;

public class OnlyHostCanDeleteMeetingException extends RuntimeException {

    public OnlyHostCanDeleteMeetingException() {
        super("Only the meeting host can delete the meeting.");
    }
}