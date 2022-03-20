package com.gumi.cursos.kstream.namesplitter.model.mapper;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.DniDTO;
import com.gumi.cursos.kstream.namesplitter.model.domain.Dni;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DniAvroMapper {

	Dni toDomain(DniDTO source);

	DniDTO toDTO(Dni source);
}
