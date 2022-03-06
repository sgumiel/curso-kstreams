package com.gumi.cursos.kstream.randomdata.nombre.service.impl;

import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;
import com.gumi.cursos.kstream.randomdata.nombre.repository.NameRepository;
import com.gumi.cursos.kstream.randomdata.nombre.service.NameService;
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
    public Name findRandomName() {

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
    public Name create(Name name) {
        return this.nameRepository.save(name);
    }
}
