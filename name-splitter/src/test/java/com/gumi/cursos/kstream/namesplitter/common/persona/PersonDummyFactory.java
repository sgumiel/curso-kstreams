package com.gumi.cursos.kstream.namesplitter.common.persona;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.model.domain.Dni;
import com.gumi.cursos.kstream.namesplitter.model.domain.Name;
import com.gumi.cursos.kstream.namesplitter.model.domain.Person;

public abstract class PersonDummyFactory {

	public static Person createDummy() {
		final var person = Person.builder()
				.age(30)
				.dni(Dni.builder()
						.number(52886401)
						.character("V")
						.build())
				.name(Name.builder()
						.name("Juan")
						.isCompuesto(false).build())
				.build();

		return person;
	}

	public static Person createPersonWithNameComposedDummy() {
		final var person = createDummy();
		person.getName().setName("David Alberto");
		person.getName().setIsCompuesto(true);
		return person;
	}

	public static Person createPersonWithNameSimpleDummy() {
		final var person = createDummy();
		person.getName().setName("Alberto");
		person.getName().setIsCompuesto(false);
		return person;
	}

	public static Person createPersonFrom(PersonDTO personDTO) {

		final var dniDTO = personDTO.getDni();
		final var nameDTO = personDTO.getName();
		final var person = Person.builder()
				.age(personDTO.getAge())
				.dni(Dni.builder()
						.number(dniDTO.getNumber())
						.character(dniDTO.getCharacter())
						.build())
				.name(Name.builder()
						.name(nameDTO.getName())
						.isCompuesto(nameDTO.getIsCompuesto())
						.build())
				.build();

		return person;

	}
}
