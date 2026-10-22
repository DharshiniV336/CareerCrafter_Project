package com.springboot.tasksystem.config;

import com.springboot.tasksystem.dto.request.ErrorDto;
import com.springboot.tasksystem.exception.InvalidCallException;
import com.springboot.tasksystem.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.badRequest().body(
                new ErrorDto(e.getMessage(), "ID not found in DB", Instant.now())
        );
    }

    @ExceptionHandler(InvalidCallException.class)
    public ResponseEntity<ErrorDto> handleInvalidCallException(InvalidCallException e){
        return ResponseEntity.badRequest()
                .body(new ErrorDto(e.getMessage(), "Call is Invalid due to malformed data",Instant.now()));
    }
}
