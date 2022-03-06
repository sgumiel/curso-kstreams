package com.gumi.cursos.kstream.randomdata.person.mapper;

import com.gumi.cursos.kstream.randomdata.dni.Dni;
import com.gumi.cursos.kstream.randomdata.person.avro.DniDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Dni2DTOMapper {

    DniDTO toDniDTO(Dni source);
}
