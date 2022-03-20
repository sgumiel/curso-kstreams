package com.gumi.cursos.kstream.namesplitter.topology.namesplitter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDtoDummyFactory;
import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDummyFactory;
import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.base.NameSplitterBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameSplitterTopologyITest extends NameSplitterBaseTest {

    @Test
    @DisplayName("Test person with composed name should be sent to the topic of composed names")
    void testPersonWithComposedNameShouldBeSentToTopicOfComposedNames() throws Exception {

        final var personDTO = PersonDtoDummyFactory.createPersonWithNameComposedDummy();
        final var person = PersonDummyFactory.createPersonFrom(personDTO);
        final var key = person.getDni().getCompleto();

        when(this.personMapper.toDomain(personDTO)).thenReturn(person);
        when(this.personMapper.toDTO(person)).thenReturn(personDTO);
        when(this.personWithNameComposed.test(key, person)).thenReturn(true);

        final String dni = new StringBuilder()
                .append(personDTO.getDni().getNumber())
                .append(personDTO.getDni().getCharacter()).toString();

        personTopic.pipeInput(dni, personDTO);

        final var data = personComposedTopic.readKeyValuesToMap();
        assertThat(data)
                .containsKey(dni)
                .containsValue(personDTO);

        assertThat(personComposedTopic.isEmpty()).isTrue();
        assertThat(personSimpleTopic.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Test person with composed name should be sent to the topic of simple names")
    void testPersonWithComposedNameShouldBeSentToTopicOfSimpleNames() throws Exception {

        final var personDTO = PersonDtoDummyFactory.createPersonWithNameSimpleDummy();
        final var person = PersonDummyFactory.createPersonFrom(personDTO);
        final var key = person.getDni().getCompleto();

        when(this.personMapper.toDomain(personDTO)).thenReturn(person);
        when(this.personMapper.toDTO(person)).thenReturn(personDTO);
        when(this.personWithNameComposed.test(key, person)).thenReturn(false);

        final String dni = new StringBuilder()
                .append(personDTO.getDni().getNumber())
                .append(personDTO.getDni().getCharacter()).toString();

        personTopic.pipeInput(dni, personDTO);

        final var data = personSimpleTopic.readKeyValuesToMap();
        assertThat(data)
                .containsKey(dni)
                .containsValue(personDTO);

        assertThat(personComposedTopic.isEmpty()).isTrue();
        assertThat(personSimpleTopic.isEmpty()).isTrue();

    }
}
