package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.NameDTO;
import com.gumi.cursos.kstream.namesplitter.model.domain.Name;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NameAvroMapper {

	Name toDomain(NameDTO source);

	NameDTO toDTO(Name source);
}
