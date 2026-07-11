package com.meetgenie.backend.exception;

import com.meetgenie.backend.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex){

        ApiResponse response =
                new ApiResponse(false, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse> handleInvalidCredentials(
            InvalidCredentialsException ex) {

        ApiResponse response =
                new ApiResponse(false, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(MeetingNotFoundException.class)
    public ResponseEntity<ApiResponse> handleMeetingNotFound(
            MeetingNotFoundException ex) {

        ApiResponse response =
                new ApiResponse(false, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(AlreadyJoinedMeetingException.class)
    public ResponseEntity<ApiResponse> handleAlreadyJoinedMeeting(
            AlreadyJoinedMeetingException ex) {

        ApiResponse response = new ApiResponse(false, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<ApiResponse> handleParticipantNotFound(
            ParticipantNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedMeetingAccessException.class)
    public ResponseEntity<ApiResponse> handleUnauthorizedMeetingAccess(
            UnauthorizedMeetingAccessException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse(false, ex.getMessage()));
    }

}