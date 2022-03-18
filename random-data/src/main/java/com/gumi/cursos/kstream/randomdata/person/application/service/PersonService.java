package com.gumi.cursos.kstream.randomdata.person.application.service;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonService {

    Person createRandom();

    Long publishRandom();

    Boolean publish(Long personId);
}
