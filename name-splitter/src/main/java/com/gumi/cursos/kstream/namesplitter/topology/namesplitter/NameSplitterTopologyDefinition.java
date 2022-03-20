package com.gumi.cursos.kstream.namesplitter.topology.namesplitter;

import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.model.infrastructure.avro.PersonDTO;
import com.gumi.cursos.kstream.namesplitter.model.mapper.PersonMapper;
import com.gumi.cursos.kstream.namesplitter.topology.namesplitter.filter.PersonWithNameComposed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class NameSplitterTopologyDefinition {

    private final KafkaTopicProperties kafkaTopicProperties;
    private final PersonWithNameComposed personWithNameComposed;
    private final PersonMapper personMapper;

    @Bean
    public Topology nameSplitterTopology(StreamsBuilder streamsBuilder){

        final var personKStream = streamsBuilder
                .<String, PersonDTO>stream(this.kafkaTopicProperties.getPerson())
                .mapValues(personMapper::toDomain);

        final var nameBranchMap = personKStream
                .split(Named.as("person-name-"))
                .branch(personWithNameComposed, Branched.as("composed"))
                .defaultBranch(Branched.as("simple"));

        final var nameComposedBranch = nameBranchMap.get("person-name-composed");
        nameComposedBranch
                .mapValues(this.personMapper::toDTO)
                .to(this.kafkaTopicProperties.getNameComposed());

        final var nameSimpleBranch = nameBranchMap.get("person-name-simple");
        nameSimpleBranch
                .mapValues(this.personMapper::toDTO)
                .to(this.kafkaTopicProperties.getNameSimple());

        return streamsBuilder.build();

    }
}