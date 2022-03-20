package com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.mapper;

import com.gumi.cursos.kstream.randomdata.dni.infrastructure.rest.mapper.DniRestMapper;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.mapper.NameRestMapper;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.dto.PersonRestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DniRestMapper.class, NameRestMapper.class})
public interface PersonRestMapper {

    PersonRestDTO toPersonDTO(Person person);
}
