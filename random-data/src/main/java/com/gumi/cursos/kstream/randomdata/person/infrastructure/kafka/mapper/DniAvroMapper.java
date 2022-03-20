package com.gumi.cursos.kstream.randomdata.person.infrastructure.kafka.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.DniDTO;
import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DniAvroMapper {

	DniDTO toPersonDTO(Dni source);
}
