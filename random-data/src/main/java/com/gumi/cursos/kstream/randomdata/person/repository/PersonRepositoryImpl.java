package com.gumi.cursos.kstream.randomdata.person.repository;

import com.gumi.cursos.kstream.randomdata.dni.domain.repository.DniRepository;
import com.gumi.cursos.kstream.randomdata.name.domain.repository.NameRepository;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.domain.repository.PersonRepository;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.mapper.PersonJpaMapper;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.repository.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository personJpaRepository;
    private final PersonJpaMapper personJpaMapper;
    private final DniRepository dniRepository;
    private final NameRepository nameRepository;

    @Override
    public Person save(Person person) {

        log.debug("Save person: {}", person);

        final var dniId = this.dniRepository
                .findIdByNumberAndCharacter(person.getDni().getNumber(), person.getDni().getCharacter())
                .orElseThrow(() -> new RuntimeException("DNI NO EXISTE"));
        log.debug("Dni id: {}", dniId);

        this.dniRepository.markAsUsed(dniId);

        final var nameId = this.nameRepository
                .findIdByName(person.getName().getName())
                .orElseThrow(() -> new RuntimeException("NAME NO EXISTE"));
        log.debug("Name id: {}", nameId);

        final var personEntity = this.personJpaMapper.toEntity(person, dniId, nameId);
        log.debug("Person mapped to entity");

        final var personEntitySaved = this.personJpaRepository.save(personEntity);
        log.debug("Person entity saved: {}", personEntitySaved);

        final var personSaved = this.personJpaMapper.toDomain(personEntity, person.getDni(), person.getName());
        log.debug("Person entity saved mapped to domain");

        return personSaved;
    }
}
