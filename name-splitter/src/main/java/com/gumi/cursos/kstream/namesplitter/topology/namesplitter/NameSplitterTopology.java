package com.gumi.cursos.kstream.namesplitter.topology.namesplitter;

import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_BRANCH_COMPUESTO;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_BRANCH_SIMPLE;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_CONSUMED_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_KSTREAM_COMPUESTO;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_KSTREAM_SIMPLE;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.PERSON_TOPIC_SPLIT_AS;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_IN_PERSON_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_OUT_PERSON_COMPUESTO_TOPIC;
import static com.gumi.cursos.kstream.namesplitter.topology.namesplitter.NameSplitterTopologyConstant.TOPIC_OUT_PERSON_SIMPLE_TOPIC;

import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NameSplitterTopology {

    @Bean
    public KStream<String, PersonDTO> kstreamNameSplitter(StreamsBuilder streamsBuilder){

        KStream<String, PersonDTO> personTopicKstream = streamsBuilder
                .stream(TOPIC_IN_PERSON_TOPIC, Consumed.as(PERSON_TOPIC_CONSUMED_AS));

        final var personNameSplitterBranches = personTopicKstream
                .peek((key, p) -> log.debug("[key: {}] read from person topic", key, p))
                .split(Named.as(PERSON_TOPIC_SPLIT_AS))
                .branch( (key, value) -> value.getName().getIsCompuesto(), Branched.as(PERSON_TOPIC_BRANCH_COMPUESTO))
                .defaultBranch(Branched.as(PERSON_TOPIC_BRANCH_SIMPLE));

        final var kstreamNameCompuesto = personNameSplitterBranches.get(PERSON_TOPIC_KSTREAM_COMPUESTO);
        kstreamNameCompuesto
                .peek((key, p) -> log.debug("[key: {}] to person compuesto topic", key, p))
                .to(TOPIC_OUT_PERSON_COMPUESTO_TOPIC);

        final var kstreamNameSimple = personNameSplitterBranches.get(PERSON_TOPIC_KSTREAM_SIMPLE);
        kstreamNameSimple
                .peek((key, p) -> log.debug("[key: {}] to person simple topic", key, p))
                .to(TOPIC_OUT_PERSON_SIMPLE_TOPIC);

        return personTopicKstream;
    }
}

