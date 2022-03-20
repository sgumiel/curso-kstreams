package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.namesplitter.model.domain.Dni;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.DniDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DniMapper {

	@Mapping(target = "number", source = "number")
	@Mapping(target = "character", source = "character")
	Dni toDomain(DniDTO source);

	DniDTO toDTO(Dni source);
}
