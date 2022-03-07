package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.repository;

import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends CrudRepository<PersonEntity, Long> {
}
