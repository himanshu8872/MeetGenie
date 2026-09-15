package com.meetgenie.backend.exception;

public class MeetingNotStartedException extends RuntimeException {

    public MeetingNotStartedException() {
        super("Meeting has not started yet.");
    }
}