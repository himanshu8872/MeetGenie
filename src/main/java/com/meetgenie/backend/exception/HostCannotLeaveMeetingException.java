package com.meetgenie.backend.exception;

public class HostCannotLeaveMeetingException extends RuntimeException {

    public HostCannotLeaveMeetingException() {
        super("Host cannot leave an active meeting. End or delete the meeting instead.");
    }
}