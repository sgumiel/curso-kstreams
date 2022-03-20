package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DniAvroMapper.class, NameAvroMapper.class})
public interface PersonAvroMapper {

	Person toDomain(PersonDTO source);

	PersonDTO toDTO(Person source);
}
