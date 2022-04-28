package com.gumi.cursos.kstream.namesplitter.statestore;

import com.gumi.cursos.kstream.namesplitter.config.ApplicationProperties;
import com.gumi.cursos.kstream.namesplitter.statestore.login.StateStoreFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StateStoreConfig {

	private final ApplicationProperties applicationProperties;
	private final StreamsBuilder streamsBuilder;

	@PostConstruct
	public void addStateStore() {
		final var personLoggedStore = StateStoreFactory.createStateStoreLogin(this.applicationProperties);
		this.streamsBuilder.addStateStore(personLoggedStore);
	}
}