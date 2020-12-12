package com.umbrella.demoSpringBoot.Controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CityExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = CityNotFoundException.class)
    public ResponseEntity<Object> exception(CityNotFoundException exception) {
        return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<Object> exception(IdNotFoundException exception) {
        return new ResponseEntity<>("id is messing", HttpStatus.BAD_REQUEST);
    }
}
