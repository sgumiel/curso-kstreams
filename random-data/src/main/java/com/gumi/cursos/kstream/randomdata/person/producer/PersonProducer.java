package com.gumi.cursos.kstream.randomdata.person.producer;

import com.gumi.cursos.kstream.randomdata.person.domain.Person;

public interface PersonProducer {

    void publish(Person person);
}
