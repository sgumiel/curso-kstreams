package com.gumi.cursos.kstream.randomdata.name.application.service;

import com.gumi.cursos.kstream.randomdata.name.domain.Name;

import java.util.List;

public interface NameService {

    Name findRandom();

    List<Name> findAll();

    Name save(Name name);
}
