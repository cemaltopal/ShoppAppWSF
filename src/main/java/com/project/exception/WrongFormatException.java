package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WrongFormatException extends RuntimeException {
    public WrongFormatException(String wrongDateMessage) {
        super(wrongDateMessage);
    }
}
