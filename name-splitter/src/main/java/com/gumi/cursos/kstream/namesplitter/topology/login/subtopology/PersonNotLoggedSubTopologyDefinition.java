package com.gumi.cursos.kstream.namesplitter.topology.login.subtopology;

import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.PERSON_LOGIN_STORE;

import com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.model.PersonLoggedCheckerResult;
import com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.transformer.PersonLoginSaveTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;

@Slf4j
public abstract class PersonNotLoggedSubTopologyDefinition {

	public static void personNotLoggedBranch(KStream<String, PersonLoggedCheckerResult> kStream,
			final PersonLoginSaveTransformer personLoginSaveTransformer) {

		kStream
				.peek((key, person) -> log.debug("[key: {}] -> Person not logged", key))
				.mapValues( personLoggedCheckerResult -> personLoggedCheckerResult.getPerson())
				.transform(() -> personLoginSaveTransformer, PERSON_LOGIN_STORE);

	}
}