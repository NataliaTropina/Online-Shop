package com.example.demo.todo.advices;

import com.example.demo.todo.dto.StandardResponseDto;
import com.example.demo.todo.exceptions.IncorrectDeleteException;
import com.example.demo.todo.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class})
    public ResponseEntity<StandardResponseDto> handleNotFound(NotFoundException ex) {
        log.error(ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StandardResponseDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }


    @ExceptionHandler(IncorrectDeleteException.class)
    public ResponseEntity<StandardResponseDto> handleIncorrectDeleteException(IncorrectDeleteException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StandardResponseDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build());
    }
}
