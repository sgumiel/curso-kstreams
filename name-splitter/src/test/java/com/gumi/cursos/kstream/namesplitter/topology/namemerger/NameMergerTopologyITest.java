package com.gumi.cursos.kstream.namesplitter.topology.namemerger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDtoDummyFactory;
import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDummyFactory;
import com.gumi.cursos.kstream.namesplitter.topology.namemerger.base.NameMergerBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameMergerTopologyITest extends NameMergerBaseTest {

	@Test
	@DisplayName("Test person from name composed topic should should be sent to login topic")
	void testPersonFromNameComposedShouldBeSentToLoginTopic() {

		final var personDTO = PersonDtoDummyFactory.createPersonWithNameComposedDummy();
		final var person = PersonDummyFactory.createPersonFrom(personDTO);
		final var key = person.getDni().getCompleto();

		when(this.personMapper.toDomain(personDTO)).thenReturn(person);
		when(this.personMapper.toDTO(person)).thenReturn(personDTO);

		final String dni = new StringBuilder()
				.append(personDTO.getDni().getNumber())
				.append(personDTO.getDni().getCharacter()).toString();

		personComposedTopic.pipeInput(dni, personDTO);

		final var data = personLoginTopic.readKeyValuesToMap();
		assertThat(data)
				.containsKey(dni)
				.containsValue(personDTO);

		assertThat(personLoginTopic.isEmpty()).isTrue();

	}

	@Test
	@DisplayName("Test person from name simple topic should should be sent to login topic")
	void testPersonFromNameSimpleShouldBeSentToLoginTopic() {

		final var personDTO = PersonDtoDummyFactory.createPersonWithNameComposedDummy();
		final var person = PersonDummyFactory.createPersonFrom(personDTO);
		final var key = person.getDni().getCompleto();

		when(this.personMapper.toDomain(personDTO)).thenReturn(person);
		when(this.personMapper.toDTO(person)).thenReturn(personDTO);

		final String dni = new StringBuilder()
				.append(personDTO.getDni().getNumber())
				.append(personDTO.getDni().getCharacter()).toString();

		personSimpleTopic.pipeInput(dni, personDTO);

		final var data = personLoginTopic.readKeyValuesToMap();
		assertThat(data)
				.containsKey(dni)
				.containsValue(personDTO);

		assertThat(personLoginTopic.isEmpty()).isTrue();

	}
}