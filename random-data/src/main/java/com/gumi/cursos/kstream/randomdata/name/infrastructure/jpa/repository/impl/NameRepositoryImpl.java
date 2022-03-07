package com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.repository.impl;

import com.gumi.cursos.kstream.randomdata.common.mapper.CommonJpaMapper;
import com.gumi.cursos.kstream.randomdata.common.repository.CommonRepositoryImpl;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.domain.repository.NameRepository;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity.NameEntity;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.mapper.NameJpaMapper;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.repository.NameJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class NameRepositoryImpl extends CommonRepositoryImpl<Name, NameEntity, Long> implements NameRepository {

    private NameJpaRepository repository;
    private NameJpaMapper mapper;

    public NameRepositoryImpl(CrudRepository<NameEntity, Long> repository,
                              CommonJpaMapper<Name, NameEntity> mapper) {
        super(repository, mapper);
        this.repository = (NameJpaRepository) repository;
        this.mapper = (NameJpaMapper)mapper;
    }

    @Override
    public Optional<Long> findIdByName(String name) {

        log.debug("Find by name: {}", name);

        final var nameOp = this.repository.findByName(name);
        log.debug("Name found: {}", nameOp.isPresent());

        return nameOp.map(NameEntity::getId);
    }
}