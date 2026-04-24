package com.rupesh.User_Service.ExceptionalHandling;

import com.rupesh.User_Service.DTOs.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

public class GlobalExceptionHandling {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handle(RuntimeException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), 400, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), 403, LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {


        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), 400, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        return new ResponseEntity<>(
                new ApiError("Something went wrong", 500, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
