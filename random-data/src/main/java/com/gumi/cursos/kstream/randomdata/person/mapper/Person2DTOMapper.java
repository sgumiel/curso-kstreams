package com.gumi.cursos.kstream.randomdata.person.mapper;

import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Dni2DTOMapper.class, Name2DTOMapper.class})
public interface Person2DTOMapper {

    PersonDTO toPersonDTO(Person person);
}
