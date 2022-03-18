package com.gumi.cursos.kstream.randomdata.person.repository;

import java.util.List;
import java.util.Optional;

import com.gumi.cursos.kstream.randomdata.common.service.RandomNumberService;
import com.gumi.cursos.kstream.randomdata.dni.domain.repository.DniRepository;
import com.gumi.cursos.kstream.randomdata.name.domain.repository.NameRepository;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.domain.repository.PersonRepository;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity.PersonEntity;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.mapper.PersonAggregateJpaMapper;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.mapper.PersonJpaMapper;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.repository.PersonAggregateJpaRepository;
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
    private final RandomNumberService randomNumberService;
    private final PersonAggregateJpaRepository personAggregateJpaRepository;
    private final PersonAggregateJpaMapper personAggregateJpaMapper;

    @Override
    public Optional<Person> findById(Long id) {

        log.debug("Find by id: {}, id");

        final var personAggregateOp = this.personAggregateJpaRepository.findById(id);
        log.debug("Person found: {}", personAggregateOp.isPresent());

        if(personAggregateOp.isEmpty()) {
            return Optional.empty();
        }

        final var personAggregateEntity = personAggregateOp.get();

        final var person = this.personAggregateJpaMapper.toDomain(personAggregateEntity);
        log.debug("Person mapped to domain");

        return Optional.of(person);
    }

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

    @Override public Optional<Long> findRandomId() {

        log.debug("Find random id");

        final var personEntitiesList = (List<PersonEntity>)this.personJpaRepository.findAll();
        log.debug("Person list size: {}", personEntitiesList.size());

        final var personListSize = personEntitiesList.size();
        if(personListSize == 0) return Optional.empty();

        final var positionRandom = this.randomNumberService.getRandomNumber(0, personListSize-1);
        log.debug("Position random: {}", positionRandom);

        final var personIdRandom = personEntitiesList.get(positionRandom).getId();
        log.debug("Person id random: {}", personIdRandom);

        return Optional.of(personIdRandom);
    }
}
