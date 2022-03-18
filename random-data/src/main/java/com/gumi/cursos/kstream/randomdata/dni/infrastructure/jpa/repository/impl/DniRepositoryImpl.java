package com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.repository.impl;

import com.gumi.cursos.kstream.randomdata.common.mapper.CommonJpaMapper;
import com.gumi.cursos.kstream.randomdata.common.repository.CommonRepositoryImpl;
import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.dni.domain.repository.DniRepository;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.entity.DniEntity;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.mapper.DniJpaMapper;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.repository.DniJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DniRepositoryImpl extends CommonRepositoryImpl<Dni, DniEntity, Long> implements DniRepository {

    private DniJpaRepository repository;
    private DniJpaMapper mapper;

    public DniRepositoryImpl(CrudRepository<DniEntity, Long> repository,
                             CommonJpaMapper<Dni, DniEntity> mapper) {
        super(repository, mapper);
        this.repository = (DniJpaRepository) repository;
        this.mapper = (DniJpaMapper) mapper;
    }

    @Override
    public List<Dni> findAllByUsedFalse() {

        log.debug("Find all by used false");

        final var dniEntitiesList = this.repository.findAllByUsedFalse();
        log.debug("Dni found: {}", dniEntitiesList.size());

        final var dniList = this.mapper.toDomain(dniEntitiesList);
        log.debug("Dni entities list mapped to domain");

        return dniList;
    }

    @Override
    public Optional<Long> findIdByNumberAndCharacter(Integer number, String character) {

        log.debug("Find id by number: {} and character: {}", number, character);

        final var dniOp = this.repository.findByNumberAndCharacter(number, character);
        log.debug("Dni found: {}", dniOp.isPresent());

        return dniOp.map(DniEntity::getId);
    }

    @Override
    public void markAsUsed(Long dniId) {

        log.debug("Mark as used: {}", dniId);

        final var dniEntity = this.repository.findById(dniId);
        log.debug("Dni entity found: {}", dniEntity.isPresent());

        dniEntity
                .map(DniEntity::getId)
                .ifPresent(this.repository::markAsUsed);

    }
}