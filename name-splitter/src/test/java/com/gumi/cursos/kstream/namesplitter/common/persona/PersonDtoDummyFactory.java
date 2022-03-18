package com.gumi.cursos.kstream.namesplitter.common.persona;

import com.gumi.cursos.kstream.randomdata.person.avro.DniDTO;
import com.gumi.cursos.kstream.randomdata.person.avro.NameDTO;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;

public abstract class PersonDtoDummyFactory {

	public static PersonDTO createDummy() {
		final var personDTO = PersonDTO.newBuilder()
				.setAge(30)
				.setDni(DniDTO.newBuilder()
						.setNumber(52886401)
						.setCharacter("V")
						.build())
				.setName(NameDTO.newBuilder()
						.setName("Juan")
						.setIsCompuesto(false).build())
				.build();

		return personDTO;
	}

	public static PersonDTO createNameCompuestoDummy() {
		final var personDto = createDummy();
		personDto.getName().setName("David Alberto");
		personDto.getName().setIsCompuesto(true);
		return personDto;
	}

	public static PersonDTO createNameSimpleDummy() {
		final var personDto = createDummy();
		personDto.getName().setName("Alberto");
		personDto.getName().setIsCompuesto(false);
		return personDto;
	}
}
