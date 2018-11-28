package com.eduardo.raspberryawards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MovieIsWinnerException extends Exception {

    public MovieIsWinnerException(String message){
        super(message);
    }
}
