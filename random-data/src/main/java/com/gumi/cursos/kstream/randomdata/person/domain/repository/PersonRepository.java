package com.gumi.cursos.kstream.randomdata.person.domain.repository;

import java.util.Optional;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonRepository {

    Optional<Person> findById(Long id);

    Person save(Person person);

    Optional<Long> findRandomId();
}
