package com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.transformer;

import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.PERSON_LOGIN_STORE;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.model.PersonLoggedCheckerResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonLoggedCheckerTransformer implements Transformer<String, PersonDTO, KeyValue<String, PersonLoggedCheckerResult>> {

	private ProcessorContext context;
	private KeyValueStore<String, PersonDTO> store;

	@Override
	public void init(ProcessorContext processorContext) {
		this.context = processorContext;
		this.store = this.context.getStateStore(PERSON_LOGIN_STORE);

	}

	@Override
	public KeyValue<String, PersonLoggedCheckerResult> transform(String s, PersonDTO person) {

		final var personFromStore = this.store.get(s);
		return KeyValue.pair(s, PersonLoggedCheckerResult.of(person, personFromStore != null));
	}

	@Override
	public void close() {

	}
}