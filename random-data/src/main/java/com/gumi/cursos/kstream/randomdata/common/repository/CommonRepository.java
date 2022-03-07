package com.gumi.cursos.kstream.randomdata.common.repository;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<DOMAIN, ENTITY, ID> {

    Optional<DOMAIN> findById(ID id);

    DOMAIN save(DOMAIN domain);

    List<DOMAIN> findAll();
}
