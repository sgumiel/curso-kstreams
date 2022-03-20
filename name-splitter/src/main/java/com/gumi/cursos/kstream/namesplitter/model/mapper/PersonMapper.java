package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DniMapper.class, NameMapper.class})
public interface PersonMapper {

	Person toDomain(PersonDTO source);

	PersonDTO toDTO(Person source);
}
