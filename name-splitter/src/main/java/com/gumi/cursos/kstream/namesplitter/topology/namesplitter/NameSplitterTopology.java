package com.gumi.cursos.kstream.namesplitter.topology.namesplitter;

import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NameSplitterTopology {

    @Autowired
    private KafkaTopicProperties kafkaTopicProperties;

    @Bean
    public KStream<String, PersonDTO> kStreamNameSplitter(StreamsBuilder streamsBuilder){

        KStream<String, PersonDTO> personKStream = streamsBuilder
                .stream(kafkaTopicProperties.getPerson(), Consumed.as("name-splitter-person-topic-consumer"));

        final var nameTypeBranchesMap = personKStream
                .peek((key, p) -> log.debug("[key: {}] read from person topic", key, p))
                .split(Named.as("person-"))
                .branch( (key, value) -> value.getName().getIsCompuesto(), Branched.as("name-composed"))
                .defaultBranch(Branched.as("name-simple"));

        final var nameComposedKStream = nameTypeBranchesMap.get("person-name-composed");
        nameComposedKStream
                .peek((key, p) -> log.debug("[key: {}] to person compuesto topic", key, p))
                .to(this.kafkaTopicProperties.getNameComposed());

        final var nameSimpleKStream = nameTypeBranchesMap.get("person-name-simple");
        nameSimpleKStream
                .peek((key, p) -> log.debug("[key: {}] to person simple topic", key, p))
                .to(this.kafkaTopicProperties.getNameSimple());

        return personKStream;
    }
}