package com.gumi.cursos.kstream.randomdata.person.service.impl;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.factory.PersonFactory;
import com.gumi.cursos.kstream.randomdata.person.producer.PersonProducer;
import com.gumi.cursos.kstream.randomdata.person.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonFactory personFactory;
    private final PersonProducer personProducer;

    @Override
    public Person createRandomAndPublish() {

        log.debug("Create and publish person");

        final var person = this.personFactory.create();
        log.debug("Person created: {}", person);

        this.personProducer.publish(person);
        log.debug("Person published");

        return person;
    }
}
