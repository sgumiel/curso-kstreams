package com.gumi.cursos.kstream.namesplitter.common.persona;

import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.DniDTO;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.NameDTO;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.PersonDTO;

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

	public static PersonDTO createPersonWithNameComposedDummy() {
		final var personDto = createDummy();
		personDto.getName().setName("David Alberto");
		personDto.getName().setIsCompuesto(true);
		return personDto;
	}

	public static PersonDTO createPersonWithNameSimpleDummy() {
		final var personDto = createDummy();
		personDto.getName().setName("Alberto");
		personDto.getName().setIsCompuesto(false);
		return personDto;
	}

	public static PersonDTO createPersonFrom(Person person) {

		final var dni = person.getDni();
		final var name = person.getName();
		final var personDTO = PersonDTO.newBuilder()
				.setAge(person.getAge())
				.setDni(DniDTO.newBuilder()
						.setNumber(dni.getNumber())
						.setCharacter(dni.getCharacter())
						.build())
				.setName(NameDTO.newBuilder()
						.setName(name.getName())
						.setIsCompuesto(name.getIsCompuesto())
						.build())
				.build();

		return personDTO;
	}
}
