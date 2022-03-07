package com.gumi.cursos.kstream.randomdata.name.application.service.impl;

import com.gumi.cursos.kstream.randomdata.name.application.service.NameService;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.domain.repository.NameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;

    @Override
    public Name findRandom() {

        log.debug("Find random name");
        final var names = (List<Name>) this.nameRepository.findAll();
        final var randomNumber = ((Double) (Math.random() * names.size())).intValue();
        final var name = names.get(randomNumber);
        log.debug("Random name picked: ", name);
        return name;
    }

    @Override
    public List<Name> findAll() {
        return (List<Name>) this.nameRepository.findAll();
    }

    @Override
    public Name save(Name name) {
        return this.nameRepository.save(name);
    }
}
