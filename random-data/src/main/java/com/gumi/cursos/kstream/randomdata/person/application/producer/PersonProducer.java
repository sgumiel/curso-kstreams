package com.gumi.cursos.kstream.randomdata.person.application.producer;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonProducer {

    void publish(Person person);
}
