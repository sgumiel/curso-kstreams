package com.gumi.cursos.kstream.randomdata.dni.application.service.impl;

import com.gumi.cursos.kstream.randomdata.dni.application.factory.DniFactory;
import com.gumi.cursos.kstream.randomdata.dni.application.service.DniService;
import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.dni.domain.repository.DniRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DniServiceImpl implements DniService {

    private final DniRepository dniRepository;
    private final DniFactory dniFactory;

    @Override
    public Dni findRandom() {

        log.debug("Find random");

        final var dniList = (List<Dni>) this.dniRepository.findAllByUsedFalse();
        final var randomNumber = ((Double) (Math.random() * dniList.size())).intValue();
        final var dni = dniList.get(randomNumber);
        log.debug("Random dni picked: ", dni);

        return dni;
    }

    @Override
    public Dni createRandom() {

        log.debug("Create random");

        final var dni = this.dniFactory.createRandom();
        log.debug("Dni created: {}", dni);

        final var dniSaved = this.dniRepository.save(dni);
        log.debug("Dni saved: {}", dni);

        return dni;
    }

    @Override
    public Dni save(Dni dni) {

        log.debug("Save dni: {}", dni);

        final var dniSaved = this.dniRepository.save(dni);
        log.debug("Dni saved: {}", dniSaved);

        return dniSaved;
    }
}