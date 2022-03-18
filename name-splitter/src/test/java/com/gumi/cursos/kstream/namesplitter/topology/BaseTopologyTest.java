package com.gumi.cursos.kstream.namesplitter.topology;

import java.util.Properties;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TopologyTestDriver;

public class BaseTopologyTest {

	protected static final String SCHEMA_REGISTRY_SCOPE = BaseTopologyTest.class.getName();
	protected static final String MOCK_SCHEMA_REGISTRY_URL = "mock://" + SCHEMA_REGISTRY_SCOPE;

	protected TopologyTestDriver testDriver;
	protected Properties propsStreamConfig;


	public BaseTopologyTest () {

		this.propsStreamConfig = new Properties();

		// Dummy properties needed for test diver
		propsStreamConfig.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
		propsStreamConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
		propsStreamConfig.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
		propsStreamConfig.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
		propsStreamConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL);

	}
}