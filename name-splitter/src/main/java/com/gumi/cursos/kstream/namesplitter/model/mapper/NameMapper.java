package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.namesplitter.model.domain.Name;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.NameDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameMapper {

	Name toDomain(NameDTO source);

	NameDTO toDTO(Name source);
}
