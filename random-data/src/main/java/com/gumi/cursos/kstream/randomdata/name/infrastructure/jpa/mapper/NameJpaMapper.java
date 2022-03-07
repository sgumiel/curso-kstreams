package com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.mapper;

import com.gumi.cursos.kstream.randomdata.common.mapper.CommonJpaMapper;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.entity.NameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameJpaMapper extends CommonJpaMapper<Name, NameEntity> {

}