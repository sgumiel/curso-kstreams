package com.gumi.cursos.kstream.randomdata.common.service.impl;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberServiceImpl implements RandomNumberService {

    @Override
    public Integer getRandomNumber(Integer min, Integer max) {
        return new Random().nextInt(max) + min;
    }
}
