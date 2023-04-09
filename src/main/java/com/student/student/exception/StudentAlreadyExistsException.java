package com.student.student.exception;

import lombok.Getter;

@Getter
public class StudentAlreadyExistsException extends RuntimeException {

    private final String code;

    public StudentAlreadyExistsException(String message, String code) {
        super(message);
        this.code = code;
    }
}
