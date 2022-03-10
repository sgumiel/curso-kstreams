package com.gumi.cursos.kstream.namesplitter.topology;


import com.gumi.cursos.kstream.randomdata.person.avro.DniDTO;
import com.gumi.cursos.kstream.randomdata.person.avro.NameDTO;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Properties;

public class NameSplitter2Test {

    private static final String SCHEMA_REGISTRY_SCOPE = NameSplitter2Test.class.getName();
    private static final String MOCK_SCHEMA_REGISTRY_URL = "mock://" + SCHEMA_REGISTRY_SCOPE;

    private TopologyTestDriver testDriver;

    private TestInputTopic<String, PersonDTO> personTopic;
    private TestOutputTopic<String, PersonDTO> personTopicCompuesto;
    private TestOutputTopic<String, PersonDTO> personTopicSimple;


    @BeforeEach
    void beforeEach() throws Exception {
        // Create topology to handle stream of users
        StreamsBuilder builder = new StreamsBuilder();
        new NameSplitterTopology().nameSplitterTopoly(builder);
        Topology topology = builder.build();

        // Dummy properties needed for test diver
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);

        // Create test driver
        testDriver = new TopologyTestDriver(topology, props);

        // Create Serdes used for test record keys and values
        Serde<String> stringSerde = Serdes.String();
        Serde<PersonDTO> avroPersonDTOSerde = new SpecificAvroSerde<>();
          //Serde<PersonDTO> avroColorSerde = new SpecificAvroSerde<>();

        // Configure Serdes to use the same mock schema registry URL
        Map<String, String> config = Map.of(
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);
        avroPersonDTOSerde.configure(config, false);
          //avroColorSerde.configure(config, false);

        // Define input and output topics to use in tests
        personTopic = testDriver.createInputTopic(
                "person-topic",
                stringSerde.serializer(),
                avroPersonDTOSerde.serializer());
        personTopicCompuesto = testDriver.createOutputTopic(
                "person-topic-compuesto",
                stringSerde.deserializer(),
                avroPersonDTOSerde.deserializer());

        personTopicSimple = testDriver.createOutputTopic(
                "person-topic-simple",
                stringSerde.deserializer(),
                avroPersonDTOSerde.deserializer());
    }

    @AfterEach
    void afterEach() {
        testDriver.close();
        MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
    }

    @Test
    void shouldPropagateUserWithFavoriteColorRed() throws Exception {

        final var personDTO = PersonDTO.newBuilder()
                .setAge(30)
                .setDni(DniDTO.newBuilder()
                        .setNumber(52886401)
                        .setCharacter("V")
                        .build())
                .setName(NameDTO.newBuilder()
                        .setName("Juan Alberto")
                        .setIsCompuesto(true).build())
                .build();

        personTopic.pipeInput("52889764V", personDTO);

        Assertions.assertThat(personTopicCompuesto.readKeyValuesToMap())
                .containsKey("52889764V")
                .containsValue(personDTO);

        Assertions.assertThat(personTopicCompuesto.isEmpty()).isTrue();
        Assertions.assertThat(personTopicSimple.isEmpty()).isTrue();


    }
}
