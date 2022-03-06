package com.gumi.cursos.kstream.randomdata.dni.service.impl;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.dni.Dni;
import com.gumi.cursos.kstream.randomdata.dni.service.DniService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DniServiceImpl implements DniService {

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
