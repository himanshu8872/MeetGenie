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

    @ExceptionHandler(HostCannotLeaveMeetingException.class)
    public ResponseEntity<ApiResponse> handleHostCannotLeaveMeeting(
            HostCannotLeaveMeetingException ex) {

        ApiResponse response = new ApiResponse(false, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(OnlyHostCanDeleteMeetingException.class)
    public ResponseEntity<ApiResponse> handleOnlyHostCanDeleteMeeting(
            OnlyHostCanDeleteMeetingException ex) {

        ApiResponse response = new ApiResponse(
                false,
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(MeetingAlreadyStartedException.class)
    public ResponseEntity<ApiResponse> handleMeetingAlreadyStarted(
            MeetingAlreadyStartedException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse(false, ex.getMessage())
        );
    }

    @ExceptionHandler(MeetingAlreadyEndedException.class)
    public ResponseEntity<ApiResponse> handleMeetingAlreadyEnded(
            MeetingAlreadyEndedException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse(false, ex.getMessage())
        );
    }

    @ExceptionHandler(MeetingNotStartedException.class)
    public ResponseEntity<ApiResponse> handleMeetingNotStarted(
            MeetingNotStartedException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse(false, ex.getMessage())
        );
    }

}