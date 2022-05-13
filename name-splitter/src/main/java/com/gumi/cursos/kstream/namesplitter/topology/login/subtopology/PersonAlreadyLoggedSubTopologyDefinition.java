package com.gumi.cursos.kstream.namesplitter.topology.login.subtopology;

import static com.gumi.cursos.kstream.namesplitter.statestore.countlogins.StateStoreCountLoginsFactory.PERSON_COUNT_LOGINS_STORE;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.LOGIN_PERSON_COUNT_LOGINS_TRANSFORMER;

import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.domain.PersonLoggedCheckerResult;
import com.gumi.cursos.kstream.namesplitter.transformer.PersonCountLoginsIncrementTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;

@Slf4j
public abstract class PersonAlreadyLoggedSubTopologyDefinition {

	public static void personAlreadyLoggedBranch(final KStream<String, PersonLoggedCheckerResult> kStream,
			final PersonCountLoginsIncrementTransformer personCountLoginsIncrementTransformer,
			final KafkaTopicProperties kafkaTopicProperties) {

		kStream
				.mapValues(PersonLoggedCheckerResult::getPerson)
				.transform(() -> personCountLoginsIncrementTransformer, Named.as(LOGIN_PERSON_COUNT_LOGINS_TRANSFORMER), PERSON_COUNT_LOGINS_STORE)
				.peek((key, person) -> log.debug("[key: {}] -> Person already logged", key))
				.to(kafkaTopicProperties.getNameComposed());
	}
}