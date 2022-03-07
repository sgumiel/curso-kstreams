package com.gumi.cursos.kstream.randomdata.dni.application.factory.impl;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.dni.application.factory.DniFactory;
import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DniFactoryImpl implements DniFactory {

    private static final String CHARACTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private final RandomNumberService randomNumberService;

    @Override
    public Dni createRandom() {

        final var min = 10000000;
        final var max = 99999999;
        int randomNumber = this.randomNumberService.getRandomNumber(min, max);
        final var character = CHARACTERS.charAt(randomNumber%23)+"";

        final var dni = new Dni(randomNumber, character);
        return dni;

    }
}
