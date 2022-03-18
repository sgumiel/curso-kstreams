package com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.controller;

import com.gumi.cursos.kstream.randomdata.person.application.service.PersonService;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    @PostMapping("create-random")
    public ResponseEntity<Person> createRandom() {

        log.debug("Person create request received");

        final var person = this.personService.createRandom();
        log.debug("Person created: {}", person);

        return ResponseEntity.ok(person);
    }

    @PostMapping("publish-random")
    public ResponseEntity<Long> publishRandom() {

        log.debug("Publish person random");

        final var publishedId = this.personService.publishRandom();
        log.debug("Person published id: {}", publishedId);

        return ResponseEntity.ok(publishedId);
    }

    @PostMapping("publish/{id}")
    public ResponseEntity<Boolean> publishById(@PathVariable("id") Long personId) {

        log.debug("Publish person id: {}", personId);

        final var published = this.personService.publish(personId);
        log.debug("Person published: {}", published);

        return ResponseEntity.ok(published);
    }
}