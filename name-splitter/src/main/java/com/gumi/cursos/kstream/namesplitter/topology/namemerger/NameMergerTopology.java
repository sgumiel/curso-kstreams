package com.gumi.cursos.kstream.namesplitter.topology.namemerger;

import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.PERSON_COMPUESTA_TOPIC_CONSUMED_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.PERSON_MERGED_CONSUMED_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.PERSON_SIMPLE_TOPIC_CONSUMED_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.TOPIC_IN_PERSON_COMPUESTA_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.TOPIC_IN_PERSON_SIMPLE_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namemerger.NameMergerTopologyConstant.TOPIC_OUT_LOGIN_TOPIC;

import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;

@Slf4j
public class NameMergerTopology {

	@Bean
	public KStream<String, PersonDTO> kstreamNameMerger(StreamsBuilder streamsBuilder){

		final var personCompuestaTopicKstream = streamsBuilder
				.<String, PersonDTO>stream(TOPIC_IN_PERSON_COMPUESTA_TOPIC, Consumed.as(PERSON_COMPUESTA_TOPIC_CONSUMED_AS+"A"));

		final var personSimpleTopicKstream = streamsBuilder
			.<String, PersonDTO>stream(TOPIC_IN_PERSON_SIMPLE_TOPIC, Consumed.as(PERSON_SIMPLE_TOPIC_CONSUMED_AS+"B"));

		personCompuestaTopicKstream
				.peek( (key, personDto) -> log.debug("(1)[key: {}], [personDTO: {}]", key, personDto))
				.merge(personSimpleTopicKstream, Named.as(PERSON_MERGED_CONSUMED_AS))
				.peek( (key, personDto) -> log.debug("(2)[key: {}], [personDTO: {}]", key, personDto))
				.to(TOPIC_OUT_LOGIN_TOPIC);

		return personCompuestaTopicKstream;

	}
}
