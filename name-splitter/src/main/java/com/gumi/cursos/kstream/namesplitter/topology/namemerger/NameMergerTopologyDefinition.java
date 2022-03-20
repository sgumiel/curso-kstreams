
package com.gumi.cursos.kstream.namesplitter.topology.namemerger;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonAvroMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class NameMergerTopologyDefinition {

	private final KafkaTopicProperties kafkaTopicProperties;
	private final PersonAvroMapper personMapper;

	@Bean
	public Topology nameMergerTopology(StreamsBuilder streamsBuilder){

		final var personNameComposedKStream = streamsBuilder
				.<String, PersonDTO>stream(this.kafkaTopicProperties.getNameComposed(), Consumed.as("name-merger-name-composed-consumer"))
				.mapValues(this.personMapper::toDomain);

		final var personNameSimpleKStream = streamsBuilder
				.<String, PersonDTO>stream(kafkaTopicProperties.getNameSimple(), Consumed.as("name-merger-name-simple-consumer"))
				.mapValues(this.personMapper::toDomain);

		personNameComposedKStream
				.merge(personNameSimpleKStream, Named.as("name-merged"))
				.peek( (key, personDto) -> log.debug("[key: {}], [person: {}]", key, personDto))
				.mapValues(this.personMapper::toDTO)
				.to(this.kafkaTopicProperties.getLogin());

		return streamsBuilder.build();

	}
}