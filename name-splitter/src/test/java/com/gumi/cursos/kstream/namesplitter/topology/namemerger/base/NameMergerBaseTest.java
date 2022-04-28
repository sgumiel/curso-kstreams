package com.gumi.cursos.kstream.namesplitter.topology.namemerger.base;

import java.util.Map;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonAvroMapper;
import com.gumi.cursos.kstream.namesplitter.topology.common.BaseTopologyTest;
import com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyDefinition;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = NameMergerTopologyDefinition.class)
public class NameMergerBaseTest extends BaseTopologyTest {

	protected TestInputTopic<String, PersonDTO> personComposedTopic;
	protected TestInputTopic<String, PersonDTO> personSimpleTopic;
	protected TestOutputTopic<String, PersonDTO> personLoginTopic;

	@Autowired
	protected Topology nameMergerTopology;

	@Autowired
	protected KafkaTopicProperties kafkaTopicProperties;

	@MockBean
	protected PersonAvroMapper personMapper;

	@BeforeEach
	public void beforeEachNameMerger() {

		// Create Serdes used for test record keys and values
		Serde<String> stringSerde = Serdes.String();
		Serde<PersonDTO> avroPersonDTOSerde = new SpecificAvroSerde<>();

		// Configure Serdes to use the same mock schema registry URL
		Map<String, String> config = Map.of(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);
		avroPersonDTOSerde.configure(config, false);

		// Create test driver
		testDriver = new TopologyTestDriver(nameMergerTopology, propsStreamConfig);

		// Define input and output topics to use in tests
		personComposedTopic = testDriver.createInputTopic(
				this.kafkaTopicProperties.getNameComposed(),
				stringSerde.serializer(),
				avroPersonDTOSerde.serializer());
		personSimpleTopic = testDriver.createInputTopic(
				this.kafkaTopicProperties.getNameSimple(),
				stringSerde.serializer(),
				avroPersonDTOSerde.serializer());
		personLoginTopic = testDriver.createOutputTopic(
				this.kafkaTopicProperties.getLogin(),
				stringSerde.deserializer(),
				avroPersonDTOSerde.deserializer());
	}

	@AfterEach
	void afterEach() {
		testDriver.close();
		MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
	}
}