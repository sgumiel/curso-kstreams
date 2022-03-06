package com.gumi.cursos.kstream.randomdata.nombre.repository;

import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends CrudRepository<Name, Long> {
}
