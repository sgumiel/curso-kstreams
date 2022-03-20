package com.gumi.cursos.kstream.namesplitter.topology.namesplitter.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonWithNameComposedTest {

	static PersonWithNameComposed personWithNameComposed;

	@BeforeAll
	static void beforeAll(){
		personWithNameComposed = new PersonWithNameComposed();
	}

	@Test
	@DisplayName("Test person with composed name should return true")
	void testPersonWithComposedNameShouldReturnTrue(){

		final var person = PersonDummyFactory.createPersonWithNameComposedDummy();
		final var key = person.getDni().getNumber()+person.getDni().getCharacter().toString();

		final var result = this.personWithNameComposed.test(key, person);

		assertTrue(result);

	}

	@Test
	@DisplayName("Test person with simple name should return false")
	void testPersonWithSimpleNameShouldReturnFalse(){

		final var person = PersonDummyFactory.createPersonWithNameSimpleDummy();
		final var key = person.getDni().getNumber()+person.getDni().getCharacter().toString();

		final var result = this.personWithNameComposed.test(key, person);

		assertFalse(result);

	}
}
