package com.gumi.cursos.kstream.randomdata.person.application.service.impl;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.person.application.factory.PersonFactory;
import com.gumi.cursos.kstream.randomdata.person.application.producer.PersonProducer;
import com.gumi.cursos.kstream.randomdata.person.application.service.PersonService;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonFactory personFactory;
    private final PersonProducer personProducer;
    private final PersonRepository personRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person createRandom() {

        log.debug("Create and publish person");

        final var person = this.personFactory.createRandom();
        log.debug("Person created: {}", person);

        final var personSaved = this.personRepository.save(person);
        log.debug("Person saved: {}", person);

        return personSaved;

    }

    @Override
    public Long publishRandom() {

        log.debug("Publish random");

        final var personIdRandomOp = this.personRepository.findRandomId();
        log.debug("Found person id random: {}", personIdRandomOp.isPresent());

        if(personIdRandomOp.isEmpty()) return -1L;

        final var personIdRandom = personIdRandomOp.get();
        log.debug("Person id random: {}", personIdRandom);

        final var personOp = this.personRepository.findById(personIdRandom);
        log.debug("Person found: {}", personOp.isPresent());

        if(personOp.isEmpty()) return -1L;

        final var person = personOp.get();

        this.personProducer.publish(person);
        log.debug("Person published");

        return personIdRandom;

    }

    @Override
    public Boolean publish(Long personId) {

        log.debug("Publish person id: {}", personId);

        final var personOp = this.personRepository.findById(personId);
        log.debug("Person found: {}", personOp.isPresent());

        if(personOp.isEmpty()) return false;

        final var person = personOp.get();

        this.personProducer.publish(person);
        log.debug("Person published");

        return true;

    }
}
