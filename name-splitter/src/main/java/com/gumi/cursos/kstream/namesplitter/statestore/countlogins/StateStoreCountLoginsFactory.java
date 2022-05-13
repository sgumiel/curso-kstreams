package com.gumi.cursos.kstream.namesplitter.statestore.countlogins;

import com.gumi.cursos.kstream.namesplitter.config.ApplicationProperties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

public abstract class StateStoreCountLoginsFactory {

	public static final String PERSON_COUNT_LOGINS_STORE = "person-count-logins-store";

	public static StoreBuilder<KeyValueStore<String, Integer>> createPersonCountLoginsStore(final ApplicationProperties applicationProperties) {

		return Stores.keyValueStoreBuilder(
				Stores.persistentKeyValueStore(PERSON_COUNT_LOGINS_STORE),
				Serdes.String(),
				Serdes.Integer());
	}
}