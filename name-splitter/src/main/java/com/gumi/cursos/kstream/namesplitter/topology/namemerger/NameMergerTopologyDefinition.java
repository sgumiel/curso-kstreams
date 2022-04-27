
package com.gumi.cursos.kstream.namesplitter.topology.namemerger;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonAvroMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NameMergerTopologyDefinition {

	@Bean
	public Topology nameMergerTopology(final StreamsBuilder streamsBuilder, final KafkaTopicProperties kafkaTopicProperties,
			final PersonAvroMapper personAvroMapper) {

		final var personNameComposedKStream = streamsBuilder
				.<String, PersonDTO>stream(kafkaTopicProperties.getNameComposed(), Consumed.as("name-merger-name-composed-consumer"))
				.mapValues(personAvroMapper::toDomain);

		final var personNameSimpleKStream = streamsBuilder
				.<String, PersonDTO>stream(kafkaTopicProperties.getNameSimple(), Consumed.as("name-merger-name-simple-consumer"))
				.mapValues(personAvroMapper::toDomain);

		personNameComposedKStream
				.merge(personNameSimpleKStream, Named.as("name-merged"))
				.peek((key, personDto) -> log.debug("[key: {}], [person: {}]", key, personDto))
				.mapValues(personAvroMapper::toDTO)
				.to(kafkaTopicProperties.getLogin());

		final Topology nameMergerTopology = streamsBuilder.build();

		return nameMergerTopology;

	}
}