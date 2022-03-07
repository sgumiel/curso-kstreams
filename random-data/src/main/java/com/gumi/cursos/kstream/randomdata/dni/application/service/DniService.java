package com.gumi.cursos.kstream.randomdata.dni.application.service;

import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;

public interface DniService {

    Dni findRandom();

    Dni createRandom();

    Dni save(Dni dni);
}