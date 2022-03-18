package com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.mapper;

import com.gumi.cursos.kstream.randomdata.name.domain.Name;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto.CreateNameRestDto;
import com.gumi.cursos.kstream.randomdata.name.infrastructure.rest.dto.NameRestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameRestMapper {

    NameRestDTO toResponse(Name source);

    Name toDomain(CreateNameRestDto source);
}
