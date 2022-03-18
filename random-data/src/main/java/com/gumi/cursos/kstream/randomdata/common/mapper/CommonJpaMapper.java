package com.gumi.cursos.kstream.randomdata.common.mapper;

import java.util.List;

public interface CommonJpaMapper<DOMAIN, ENTITY> extends CommonMapper<DOMAIN, ENTITY> {

    DOMAIN toDomain(ENTITY source);

    List<DOMAIN> toDomain(List<ENTITY> source);

    ENTITY toEntity(DOMAIN source);

    List<ENTITY> toEntity(List<DOMAIN> source);

}