package com.gumi.cursos.kstream.namesplitter.topology.login;

import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.PERSON_LOGIN_STORE;
import static org.assertj.core.api.Assertions.assertThat;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDtoDummyFactory;
import com.gumi.cursos.kstream.namesplitter.common.persona.PersonDummyFactory;
import com.gumi.cursos.kstream.namesplitter.topology.login.base.LoginBaseTest;
import org.apache.kafka.streams.state.KeyValueStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTopologyITest extends LoginBaseTest {

    @Test
    @DisplayName("Test save person in store when does not exists in store")
    void givenAPersonDTOWhoDoesNotExistsInStoreWhenIspublishedInPersonLoginTopicThenItIsAddedToTheStore() throws Exception {

        final var personDTO = PersonDtoDummyFactory.createPersonWithNameComposedDummy();
        final var person = PersonDummyFactory.createPersonFrom(personDTO);
        final var key = person.getDni().getCompleto();

        personLoginTopic.pipeInput(key, personDTO);

        KeyValueStore<String, PersonDTO> personLoginStore = testDriver.getKeyValueStore(PERSON_LOGIN_STORE);
        final var personFromStore = personLoginStore.get(key);
        assertThat(personFromStore).isEqualTo(personDTO);



    }
}