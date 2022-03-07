package com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.mapper;

import com.gumi.cursos.kstream.randomdata.common.mapper.CommonJpaMapper;
import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.entity.DniEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DniJpaMapper extends CommonJpaMapper<Dni, DniEntity> {

}