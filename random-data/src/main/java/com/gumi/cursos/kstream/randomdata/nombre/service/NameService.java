package com.gumi.cursos.kstream.randomdata.nombre.service;

import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;

import java.util.List;

public interface NameService {

    Name findRandomName();

    List<Name> findAll();

    Name create(Name name);
}
