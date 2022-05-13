package com.gumi.cursos.kstream.namesplitter.statestore;

import static com.gumi.cursos.kstream.namesplitter.statestore.countlogins.StateStoreCountLoginsFactory.createPersonCountLoginsStore;
import static com.gumi.cursos.kstream.namesplitter.statestore.login.StateStoreLoginFactory.createPersonLoginStore;

import com.gumi.cursos.kstream.namesplitter.config.ApplicationProperties;
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
		final var personLoginStore = createPersonLoginStore(this.applicationProperties);
		this.streamsBuilder.addStateStore(personLoginStore);

		final var personCountLoginsStore = createPersonCountLoginsStore(this.applicationProperties);
		this.streamsBuilder.addStateStore(personCountLoginsStore);
	}
}