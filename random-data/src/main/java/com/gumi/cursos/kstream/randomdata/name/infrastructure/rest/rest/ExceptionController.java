package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> catchException(DataIntegrityViolationException dive) {

        return ResponseEntity.ok(dive.getMessage());
    }
}
