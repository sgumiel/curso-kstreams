package com.gumi.cursos.kstream.namesplitter.model.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {

	private String name;
	private Boolean isCompuesto;

}