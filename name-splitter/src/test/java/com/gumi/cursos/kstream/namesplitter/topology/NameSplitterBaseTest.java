package com.gumi.cursos.kstream.namesplitter.topology;

import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_IN_PERSON_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_OUT_PERSON_COMPUESTO_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_OUT_PERSON_SIMPLE_TOPIC;

import java.util.Map;

import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopology;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class NameSplitterBaseTest extends BaseTopologyTest {

	protected TestInputTopic<String, PersonDTO> personTopic;
	protected TestOutputTopic<String, PersonDTO> personTopicCompuesto;
	protected TestOutputTopic<String, PersonDTO> personTopicSimple;

	@BeforeEach
	public void beforeachNameSplitter() {

		// Create topology to handle stream of users
		StreamsBuilder builder = new StreamsBuilder();
		new NameSplitterTopology().kstreamNameSplitter(builder);
		Topology topology = builder.build();

		// Create Serdes used for test record keys and values
		Serde<String> stringSerde = Serdes.String();
		Serde<PersonDTO> avroPersonDTOSerde = new SpecificAvroSerde<>();

		// Configure Serdes to use the same mock schema registry URL
		Map<String, String> config = Map.of(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);
		avroPersonDTOSerde.configure(config, false);

		// Create test driver
		testDriver = new TopologyTestDriver(topology, propsStreamConfig);

		// Define input and output topics to use in tests
		personTopic = testDriver.createInputTopic(
				TOPIC_IN_PERSON_TOPIC,
				stringSerde.serializer(),
				avroPersonDTOSerde.serializer());
		personTopicCompuesto = testDriver.createOutputTopic(
				TOPIC_OUT_PERSON_COMPUESTO_TOPIC,
				stringSerde.deserializer(),
				avroPersonDTOSerde.deserializer());
		personTopicSimple = testDriver.createOutputTopic(
				TOPIC_OUT_PERSON_SIMPLE_TOPIC,
				stringSerde.deserializer(),
				avroPersonDTOSerde.deserializer());
	}

	@AfterEach
	void afterEach() {
		testDriver.close();
		MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
	}
}