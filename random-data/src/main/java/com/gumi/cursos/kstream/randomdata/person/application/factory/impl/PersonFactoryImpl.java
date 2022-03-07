package com.gumi.cursos.kstream.randomdata.person.application.factory.impl;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.dni.application.service.DniService;
import com.gumi.cursos.kstream.randomdata.name.application.service.NameService;
import com.gumi.cursos.kstream.randomdata.person.application.factory.PersonFactory;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonFactoryImpl implements PersonFactory {

    private final NameService nameService;
    private final DniService dniService;
    private final RandomNumberService randomNumberService;

    @Override
    public Person createRandom() {

        log.debug("Creating random person");

        final var name = nameService.findRandom();
        final var dni = dniService.findRandom();
        final var age = this.randomNumberService.getRandomNumber(1,99);
        final var person = new Person(dni, name, age);
        log.debug("Person created: {}", person);

        return person;
    }
}
