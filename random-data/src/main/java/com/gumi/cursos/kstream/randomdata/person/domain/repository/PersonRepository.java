package com.gumi.cursos.kstream.randomdata.person.domain.repository;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonRepository {

    Person save(Person person);
}
