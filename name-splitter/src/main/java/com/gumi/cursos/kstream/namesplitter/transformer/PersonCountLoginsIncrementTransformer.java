package com.gumi.cursos.kstream.namesplitter.transformer;

import static com.gumi.cursos.kstream.namesplitter.statestore.countlogins.StateStoreCountLoginsFactory.PERSON_COUNT_LOGINS_STORE;

import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonCountLoginsIncrementTransformer implements Transformer<String, Person, KeyValue<String, Person>> {

	private ProcessorContext context;
	private KeyValueStore<String, Integer> store;

	@Override
	public void init(ProcessorContext processorContext) {
		this.context = processorContext;
		this.store = this.context.getStateStore(PERSON_COUNT_LOGINS_STORE);

	}

	@Override
	public KeyValue<String, Person> transform(String key, Person person) {

		var timesLogged = this.store.get(key);
		log.debug("[key: {}] -> person logged: {} times", key, timesLogged);
		timesLogged++;
		this.store.put(key, timesLogged);
		return KeyValue.pair(key, person);
	}

	@Override
	public void close() {

	}
}