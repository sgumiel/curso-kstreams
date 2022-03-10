package com.gumi.cursos.kstream.namesplitter.topology;

import com.gumi.cursos.kstream.randomdata.person.avro.NameDTO;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Properties;

@ExtendWith(SpringExtension.class)
public class NameSplitterTopologyTest {



    @Test
    @DisplayName("Given a person with a composed name, this person will be sent to person-topic-compuesto")
    void given1(){

        final MockSchemaRegistryClient schemaRegistryClient = new MockSchemaRegistryClient();

        // Mock needs to be passed to application!

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        final var nameSplitterTopology = new NameSplitterTopology();
        final KStream<String, PersonDTO> kstreamPersonDTO = nameSplitterTopology.nameSplitterTopoly(streamsBuilder);
        Topology topology = streamsBuilder.build();

        final var serdeKey = Serdes.String();
        final var serdeValue = new SpecificAvroSerde<PersonDTO>();

        Properties props = new Properties();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);

        try (TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology, props)) {
            TestInputTopic<String, PersonDTO> personTopic = topologyTestDriver
                    .createInputTopic("person-topic", serdeKey.serializer(), serdeValue.serializer());

            TestOutputTopic<String, PersonDTO> personTopicCompuesto = topologyTestDriver
                    .createOutputTopic("person-topic-compuesto", serdeKey.deserializer(), serdeValue.deserializer());
            TestOutputTopic<String, PersonDTO> personTopicSimple = topologyTestDriver
                    .createOutputTopic("person-topic-simple", serdeKey.deserializer(), serdeValue.deserializer());

            final var personDTO = PersonDTO.newBuilder()
                    .setName(NameDTO.newBuilder()
                            .setName("Juan Alberto")
                            .setIsCompuesto(true).build())
                    .build();

            personTopic.pipeInput("52889764V", personDTO);

            Assertions.assertThat(personTopicCompuesto.readKeyValuesToMap())
                    .containsKey("52889764V")
                    .containsValue(personDTO);

            Assertions.assertThat(personTopicSimple.isEmpty()).isTrue();
        }
    }
}
