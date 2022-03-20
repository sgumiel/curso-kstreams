/*
package com.gumi.cursos.kstream.namesplitter.topology.namemerger;


import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Slf4j
public class NameMergerTopology {

	@Autowired
	private KafkaTopicProperties kafkaTopicProperties;

	@Bean
	public KStream<String, PersonDTO> kstreamNameMerger(StreamsBuilder streamsBuilder){

		final var personNameComposedKstream = streamsBuilder
				.<String, PersonDTO>stream(kafkaTopicProperties.getNameComposed(), Consumed.as("name-merger-name-composed-consumer"));

		final var personNameSimpleKstream = streamsBuilder
			.<String, PersonDTO>stream(kafkaTopicProperties.getNameSimple(), Consumed.as("name-merger-name-simple-consumer"));

		personNameComposedKstream
				.merge(personNameSimpleKstream, Named.as("name-merged"))
				.peek( (key, personDto) -> log.debug("[key: {}], [personDTO: {}]", key, personDto))
				.to(kafkaTopicProperties.getLogin());

		return personNameComposedKstream;

	}
}
*/