package com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.controller;

import com.gumi.cursos.kstream.randomdata.person.application.service.PersonService;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create() {

        log.debug("Person create request received");

        final var person = this.personService.createRandomAndPublish();
        log.debug("Person created: {}", person);

        return ResponseEntity.ok(person);
    }
}