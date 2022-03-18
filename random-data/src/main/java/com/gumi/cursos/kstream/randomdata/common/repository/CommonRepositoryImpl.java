package com.gumi.cursos.kstream.randomdata.common.repository;

import com.gumi.cursos.kstream.randomdata.common.mapper.CommonJpaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class CommonRepositoryImpl<DOMAIN, ENTITY, ID> implements CommonRepository<DOMAIN, ENTITY, ID>{

    private CrudRepository<ENTITY, ID> repository;
    private CommonJpaMapper<DOMAIN, ENTITY> mapper;

    public CommonRepositoryImpl(CrudRepository<ENTITY, ID> repository, CommonJpaMapper<DOMAIN, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DOMAIN save(DOMAIN domain) {
        log.debug("Save domain object: {}", domain);

        final var entity = this.mapper.toEntity(domain);
        log.debug("Domain object mapped to entity");

        final var entitySaved = this.repository.save(entity);
        log.debug("Entity saved: {}", entitySaved);

        final var domainSaved = this.mapper.toDomain(entitySaved);
        log.info("Entity saved mapped to domain");

        return domainSaved;
    }

    @Override
    public List<DOMAIN> findAll(){

        log.debug("Find all");

        final List<DOMAIN> entities = (List)this.repository.findAll();
        log.debug("Entities found: {}", entities.size());

        final var domainList = this.mapper.toDomain((List)entities);
        log.debug("Entities mapped to domain");

        return domainList;
    }

    @Override
    public Optional<DOMAIN> findById(ID id){

        log.debug("Find by id: {}", id);

        final var entityOp = this.repository.findById(id);
        log.debug("Entity found: {}", entityOp.isPresent());

        final var domainOp = entityOp
                .map(this.mapper::toDomain);
        log.debug("Entity mapped to domain");

        return domainOp;

    }
}