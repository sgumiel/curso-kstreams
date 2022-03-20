package com.gumi.cursos.kstream.namesplitter.topology.namesplitter.base;

import java.util.Map;

import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonMapper;
import com.gumi.cursos.kstream.namesplitter.topology.common.BaseTopologyTest;
import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyDefinition;
import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.config.NameSplitterTopologyTestConfig;
import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.filter.PersonWithNameComposed;
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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@EnableAutoConfiguration
@Import({KafkaTopicProperties.class, NameSplitterTopologyTestConfig.class })
@SpringBootTest(classes = NameSplitterTopologyDefinition.class)
public class NameSplitterBaseTest extends BaseTopologyTest {

	protected TestInputTopic<String, PersonDTO> personTopic;
	protected TestOutputTopic<String, PersonDTO> personComposedTopic;
	protected TestOutputTopic<String, PersonDTO> personSimpleTopic;

	@Autowired
	protected Topology nameSplitterTopology;

	@Autowired
	protected KafkaTopicProperties kafkaTopicProperties;

	@MockBean
	protected PersonWithNameComposed personWithNameComposed;

	@MockBean
	protected PersonMapper personMapper;

	@BeforeEach
	public void beforeachNameSplitter() {

		// Create Serdes used for test record keys and values
		Serde<String> stringSerde = Serdes.String();
		Serde<PersonDTO> avroPersonDTOSerde = new SpecificAvroSerde<>();

		// Configure Serdes to use the same mock schema registry URL
		Map<String, String> config = Map.of(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);
		avroPersonDTOSerde.configure(config, false);

		// Create test driver
		testDriver = new TopologyTestDriver(nameSplitterTopology, propsStreamConfig);

		// Define input and output topics to use in tests
		personTopic = testDriver.createInputTopic(
				this.kafkaTopicProperties.getPerson(),
				stringSerde.serializer(),
				avroPersonDTOSerde.serializer());
		personComposedTopic = testDriver.createOutputTopic(
				this.kafkaTopicProperties.getNameComposed(),
				stringSerde.deserializer(),
				avroPersonDTOSerde.deserializer());
		personSimpleTopic = testDriver.createOutputTopic(
				this.kafkaTopicProperties.getNameSimple(),
				stringSerde.deserializer(),
				avroPersonDTOSerde.deserializer());
	}

	@AfterEach
	void afterEach() {
		testDriver.close();
		MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
	}
}