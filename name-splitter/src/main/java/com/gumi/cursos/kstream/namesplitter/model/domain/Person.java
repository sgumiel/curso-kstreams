package com.gumi.cursos.kstream.namesplitter.model.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

	private Dni dni;
	private Name name;
	private Integer age;
}
