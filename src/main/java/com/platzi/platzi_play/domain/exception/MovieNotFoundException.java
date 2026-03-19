package com.platzi.platzi_play.domain.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String message){
        super(message);
    }
}
