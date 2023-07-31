package com.task.ergo.exception;

public class PersonException extends RuntimeException {
    public PersonException(String errorMessage) {
        super(errorMessage);
    }
}
