package com.gumi.cursos.kstream.randomdata.person.infrastructure.kafka.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.NameDTO;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameAvroMapper {

	NameDTO toPersonDTO(Name source);
}
