package com.gumi.cursos.kstream.namesplitter.topology;

import static org.assertj.core.api.Assertions.assertThat;

import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDtoDummyFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NameSplitterTopologyTest extends NameSplitterBaseTest {

    @Test
    @DisplayName("Test person with composed name should be sent to person-topic-compuesto")
    void testPersonWithComposedNameShouldBeSentToPersonTopicCompuesto() throws Exception {

        final var personDTO = PersonDtoDummyFactory.createNameCompuestoDummy();
        final String dni = new StringBuilder()
                .append(personDTO.getDni().getNumber())
                .append(personDTO.getDni().getCharacter()).toString();

        personTopic.pipeInput(dni, personDTO);

        assertThat(personTopicCompuesto.readKeyValuesToMap())
                .containsKey(dni)
                .containsValue(personDTO);

        assertThat(personTopicCompuesto.isEmpty()).isTrue();
        assertThat(personTopicSimple.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Test person with simple name should be sent to person-topic-simple")
    void testPersonWithSimpleNameShouldBeSentToPersonTopicSimple() throws Exception {

        final var personDTO = PersonDtoDummyFactory.createNameSimpleDummy();
        final String dni = new StringBuilder()
                .append(personDTO.getDni().getNumber())
                .append(personDTO.getDni().getCharacter()).toString();

        personTopic.pipeInput(dni, personDTO);

        assertThat(personTopicSimple.readKeyValuesToMap())
                .containsKey(dni)
                .containsValue(personDTO);

        assertThat(personTopicCompuesto.isEmpty()).isTrue();
        assertThat(personTopicSimple.isEmpty()).isTrue();

    }
}
