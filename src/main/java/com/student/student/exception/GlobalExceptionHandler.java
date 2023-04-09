package com.student.student.exception;

import com.student.student.model.ResponseError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public Mono<ResponseEntity<ResponseError<String>>> handleStudentException(StudentAlreadyExistsException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ResponseError.<String>builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build()));
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ResponseError<String>>> handleStudentException(NotFoundException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseError.<String>builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ResponseError<List<String>>>> handleBadRequestException(WebExchangeBindException e) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseError.<List<String>>builder()
                        .message(e.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .distinct()
                                .collect(Collectors.toList()))
                        .code("TL0003")
                        .build()));
    }


}
