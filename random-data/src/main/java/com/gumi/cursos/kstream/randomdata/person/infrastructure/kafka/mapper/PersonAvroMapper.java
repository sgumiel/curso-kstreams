package com.gumi.cursos.kstream.randomdata.person.infrastructure.kafka.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DniAvroMapper.class, NameAvroMapper.class})
public interface PersonAvroMapper {

	PersonDTO toPersonDTO(Person person);
}
