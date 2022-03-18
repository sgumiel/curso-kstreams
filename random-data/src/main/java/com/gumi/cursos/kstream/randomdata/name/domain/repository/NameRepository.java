package com.gumi.cursos.kstream.randomdata.name.domain.repository;

import com.gumi.cursos.kstream.randomdata.common.repository.CommonRepository;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity.NameEntity;

import java.util.Optional;

public interface NameRepository extends CommonRepository<Name, NameEntity, Long> {

    Optional<Long> findIdByName(String name);

}
