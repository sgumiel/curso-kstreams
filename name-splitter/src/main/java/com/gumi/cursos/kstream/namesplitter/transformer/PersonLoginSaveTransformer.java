package com.gumi.cursos.kstream.namesplitter.transformer;

import static com.gumi.cursos.kstream.namesplitter.statestore.login.StateStoreLoginFactory.PERSON_LOGIN_STORE;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonAvroMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonLoginSaveTransformer implements Transformer<String, Person, KeyValue<String, Person>> {

	private ProcessorContext context;
	private KeyValueStore<String, PersonDTO> store;

	private final PersonAvroMapper personAvroMapper;

	@Override
	public void init(ProcessorContext processorContext) {
		this.context = processorContext;
		this.store = this.context.getStateStore(PERSON_LOGIN_STORE);

	}

	@Override
	public KeyValue<String, Person> transform(String key, Person person) {

		final var personDTO = this.personAvroMapper.toDTO(person);
		this.store.put(key, personDTO);
		log.debug("[key: {}] -> person saved into store: {}", key, PERSON_LOGIN_STORE);
		return KeyValue.pair(key, person);
	}

	@Override
	public void close() {

	}
}
