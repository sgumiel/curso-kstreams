package com.gumi.cursos.kstream.namesplitter.topology.login.base;

import java.util.Map;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaStreamsConfig;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.topology.common.BaseTopologyTest;
import com.gumi.cursos.kstream.namesplitter.topology.login.LoginTopologyDefinition;
import com.gumi.cursos.kstream.namesplitter.transformer.PersonLoggedCheckerTransformer;
import com.gumi.cursos.kstream.namesplitter.transformer.PersonLoginSaveTransformer;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
		LoginTopologyDefinition.class,
		PersonLoginSaveTransformer.class,
		PersonLoggedCheckerTransformer.class })
public class LoginBaseTest extends BaseTopologyTest {

	protected TestInputTopic<String, PersonDTO> personLoginTopic;

	@Autowired
	protected Topology loginTopology;

	@Autowired
	protected KafkaTopicProperties kafkaTopicProperties;

	@Autowired
	protected KafkaStreamsConfig kafkaStreamsConfig;

	@BeforeEach
	public void beforeEachLoginTopology() {

		// Create Serdes used for test record keys and values
		Serde<String> stringSerde = Serdes.String();
		Serde<PersonDTO> avroPersonDTOSerde = new SpecificAvroSerde<>();

		// Configure Serdes to use the same mock schema registry URL
		Map<String, String> config = Map.of(
				AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);
		avroPersonDTOSerde.configure(config, false);

		// Create test driver
		testDriver = new TopologyTestDriver(loginTopology, propsStreamConfig);

		// Define input and output topics to use in tests
		personLoginTopic = testDriver.createInputTopic(
				this.kafkaTopicProperties.getLogin(),
				stringSerde.serializer(),
				avroPersonDTOSerde.serializer());
	}

	@AfterEach
	void afterEach() {
		testDriver.close();
		MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
	}
}