package com.gumi.cursos.kstream.namesplitter.statestore.login;

import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.PERSON_LOGIN_STORE;
import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import java.util.HashMap;
import java.util.Map;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.ApplicationProperties;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

public abstract class StateStoreFactory {

	public static StoreBuilder<KeyValueStore<String, PersonDTO>> createStateStoreLogin(final ApplicationProperties applicationProperties) {
		final var avroSerde = new SpecificAvroSerde<PersonDTO>();
		Map<String, String> config = new HashMap<>();
		config.put(SCHEMA_REGISTRY_URL_CONFIG, applicationProperties.getSchemaRegistryUrl());
		avroSerde.configure(config, false);

		return Stores.keyValueStoreBuilder(
				Stores.persistentKeyValueStore(PERSON_LOGIN_STORE),
				Serdes.String(),
				avroSerde);
	}
}