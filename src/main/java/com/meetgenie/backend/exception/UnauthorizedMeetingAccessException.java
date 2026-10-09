package com.meetgenie.backend.exception;

public class UnauthorizedMeetingAccessException extends RuntimeException {

    public UnauthorizedMeetingAccessException(String message) {
        super(message);
    }
}