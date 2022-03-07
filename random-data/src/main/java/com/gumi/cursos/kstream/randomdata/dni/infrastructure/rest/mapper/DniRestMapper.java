package com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.mapper;

import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.dto.DniRestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DniRestMapper {

    DniRestDTO toRestDTO(Dni source);


}
