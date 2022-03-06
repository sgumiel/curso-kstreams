package com.gumi.cursos.kstream.randomdata.person.mapper;

import com.gumi.cursos.kstream.randomdata.nombre.domain.Name;
import com.gumi.cursos.kstream.randomdata.person.avro.NameDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Name2DTOMapper {

    NameDTO toNameDTO(Name source);
}
