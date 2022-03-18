package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.repository;

import java.util.Optional;

import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity.PersonAggregateEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAggregateJpaRepository extends CrudRepository<PersonAggregateEntity, Long> {

	@EntityGraph(attributePaths = {"dni", "name"})
	Optional<PersonAggregateEntity> findById(Long id);
}
