package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> catchException(ConstraintViolationException cve) {

        return ResponseEntity.ok(cve.getSQLException().getMessage());
    }
}
