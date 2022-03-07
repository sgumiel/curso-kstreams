package com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.repository;

import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity.NameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameJpaRepository extends CrudRepository<NameEntity, Long> {

    Optional<NameEntity> findByName(String name);
}
