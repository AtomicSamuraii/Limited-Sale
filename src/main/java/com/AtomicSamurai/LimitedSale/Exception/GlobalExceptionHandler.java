package com.AtomicSamurai.LimitedSale.Exception;


import com.AtomicSamurai.LimitedSale.dto.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException ex){
        ExceptionResponse response = new ExceptionResponse(404, ex.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException ex){
        ExceptionResponse response = new ExceptionResponse(400, ex.getMessage(), LocalDateTime.now());

        return  ResponseEntity.status(400).body(response);
    }
}
