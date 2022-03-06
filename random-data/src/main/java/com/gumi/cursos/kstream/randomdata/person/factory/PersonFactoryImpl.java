package com.gumi.cursos.kstream.randomdata.person.factory;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.dni.service.DniService;
import com.gumi.cursos.kstream.randomdata.nombre.service.NameService;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonFactoryImpl implements PersonFactory{

    private final NameService nameService;
    private final DniService dniService;
    private final RandomNumberService randomNumberService;

    @Override
    public Person create() {

        log.debug("Creating person");

        final var name = nameService.findRandomName();
        final var dni = dniService.createRandom();
        final var age = this.randomNumberService.getRandomNumber(1,99);
        final var person = new Person(dni, name, age);
        log.debug("Person created: {}", person);

        return person;
    }
}
