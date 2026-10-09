package com.meetgenie.backend.exception;

public class MeetingAlreadyStartedException extends RuntimeException {

    public MeetingAlreadyStartedException() {
        super("Meeting has already started.");
    }
}