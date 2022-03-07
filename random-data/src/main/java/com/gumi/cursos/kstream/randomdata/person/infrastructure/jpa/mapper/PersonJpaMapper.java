package com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.mapper;

import com.gumi.cursos.kstream.randomdata.dni.domain.Dni;
import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.jpa.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonJpaMapper {

    @Mapping(target= "nameId", source="nameId")
    @Mapping(target= "dniId", source="dniId")
    PersonEntity toEntity(Person source, Long dniId, Long nameId);

    @Mapping(target= "name", source="name")
    @Mapping(target= "dni", source="dni")
    Person toDomain(PersonEntity source, Dni dni, Name name);
}
