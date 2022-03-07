package com.gumi.cursos.kstream.randomdata.person.application.service;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonService {

    Person createRandomAndPublish();
}
