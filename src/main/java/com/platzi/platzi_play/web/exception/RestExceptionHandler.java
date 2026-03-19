package com.platzi.platzi_play.web.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.platzi.platzi_play.domain.Gender;
import com.platzi.platzi_play.domain.exception.MovieAlreadyExistsException;
import com.platzi.platzi_play.domain.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handlerException(MovieAlreadyExistsException ex){
        Error error = new Error("movie-already-exists",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Error> handlerException(MovieNotFoundException ex){
        Error error = new Error("movie-not-found",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handlerException(MethodArgumentNotValidException ex){
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.add(new Error(error.getField(),error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handlerException(Exception ex){
        Error error = new Error("unknown-error",ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException e) {
        String type = "format-validation-error";
        String message = "Ha ocurrido un error en el formato de los datos enviados.";

        if (e.getCause() instanceof InvalidFormatException cause && cause.getTargetType() == Gender.class) {
            type = "genre-not-found";
            message = "El género enviado no es válido. Por favor, verifique los valores permitidos.";
        }

        Error error = new Error(type, message);
        return ResponseEntity.badRequest().body(error);
    }
}
