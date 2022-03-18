package com.gumi.cursos.kstream.randomdata.dni.domain.repository;

import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;

import java.util.List;
import java.util.Optional;

public interface DniRepository {

    Optional<Dni> findById(Long id);
    
    Dni save(Dni dni);

    List<Dni> findAll();

    List<Dni> findAllByUsedFalse();

    Optional<Long> findIdByNumberAndCharacter(Integer number, String character);

    void markAsUsed(Long dniId);
}
