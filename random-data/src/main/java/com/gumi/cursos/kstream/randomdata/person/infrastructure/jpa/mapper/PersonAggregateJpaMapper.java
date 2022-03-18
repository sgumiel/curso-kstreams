package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.mapper;

import com.gumi.cursos.kstream.randomdata.dni.infrastructure.jpa.mapper.DniJpaMapper;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.jpa.mapper.NameJpaMapper;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity.PersonAggregateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DniJpaMapper.class, NameJpaMapper.class })
public interface PersonAggregateJpaMapper {

    Person toDomain(PersonAggregateEntity source);

}
