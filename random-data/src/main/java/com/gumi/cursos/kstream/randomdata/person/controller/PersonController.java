package com.gumi.cursos.kstream.randomdata.person.controller;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.service.PersonService;
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

        log.debug("Person create request recieved");

        final var person = this.personService.createRandomAndPublish();
        log.debug("Person created: {}", person);

        return ResponseEntity.ok(person);
    }
}
