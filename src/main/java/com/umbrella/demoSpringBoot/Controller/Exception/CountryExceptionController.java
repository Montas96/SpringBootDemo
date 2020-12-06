package com.umbrella.demoSpringBoot.Controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CountryExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = CountryNotFoundException.class)
    public ResponseEntity<Object> exception(CountryNotFoundException exception) {
        return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
    }
}
