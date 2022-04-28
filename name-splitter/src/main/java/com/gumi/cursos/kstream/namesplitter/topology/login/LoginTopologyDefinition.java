package com.gumi.cursos.kstream.namesplitter.topology.login;

import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.BRANCHED_AS_ALREADY_LOGGED;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.BRANCHED_AS_NOT_LOGGED;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.BRANCH_ALREADY_LOGGED;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.BRANCH_NOT_LOGGED;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.CONSUMED_AS_LOGIN_CONSUMER;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.PERSON_LOGIN_STORE;
import static com.gumi.cursos.kstream.namesplitter.topology.login.constant.LoginTopologyConstant.SPLIT_NAMED_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.login.subtopology.PersonAlreadyLoggedSubTopologyDefinition.personAlreadyLoggedBranch;
import static com.gumi.cursos.kstream.namesplitter.topology.login.subtopology.PersonNotLoggedSubTopologyDefinition.personNotLoggedBranch;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.transformer.PersonLoggedCheckerTransformer;
import com.gumi.cursos.kstream.namesplitter.transformer.PersonLoginSaveTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LoginTopologyDefinition {

	@Bean
	public Topology loginTopology(StreamsBuilder streamsBuilder,
			final KafkaTopicProperties kafkaTopicProperties,
			final PersonLoginSaveTransformer personLoginSaveTransformer,
			final PersonLoggedCheckerTransformer personLoggedCheckerTransformer) {

		final var loginKStream = streamsBuilder
				.<String, PersonDTO>stream(kafkaTopicProperties.getLogin(), Consumed.as(CONSUMED_AS_LOGIN_CONSUMER));

		final var branches = loginKStream
				.peek((key, person) -> log.debug("[key: {}], [person: {}]", key, person))
				.transform(() -> personLoggedCheckerTransformer, Named.as("personLoggedCheckerTransformer"), PERSON_LOGIN_STORE)
				.peek((key, personLoggedCheckerResult) -> log.debug("[key: {}], [personLoggedCheckerResult: {}]", key, personLoggedCheckerResult.toString()))
				.split(Named.as(SPLIT_NAMED_AS))
				.branch((key, person) -> person.isLogged(), Branched.as(BRANCHED_AS_ALREADY_LOGGED))
				.defaultBranch(Branched.as(BRANCHED_AS_NOT_LOGGED));

		final var personLoggedBranch = branches.get(BRANCH_ALREADY_LOGGED);
		final var personNotLoggedBranch = branches.get(BRANCH_NOT_LOGGED);

		personAlreadyLoggedBranch(personLoggedBranch);
		personNotLoggedBranch(personNotLoggedBranch, personLoginSaveTransformer);

		return streamsBuilder.build();
	}
}