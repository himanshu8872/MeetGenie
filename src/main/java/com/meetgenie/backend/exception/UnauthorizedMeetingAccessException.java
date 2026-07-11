package com.meetgenie.backend.exception;

public class UnauthorizedMeetingAccessException
        extends RuntimeException {

    public UnauthorizedMeetingAccessException() {
        super("You are not authorized to access this meeting.");
    }
}