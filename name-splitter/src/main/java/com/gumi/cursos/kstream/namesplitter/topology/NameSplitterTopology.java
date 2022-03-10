package com.gumi.cursos.kstream.namesplitter.topology;

import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NameSplitterTopology {

    @Bean
    public KStream<String, PersonDTO> nameSplitterTopoly(StreamsBuilder streamsBuilder){

        KStream<String, PersonDTO> personDTOKStream = streamsBuilder
                .stream("person-topic", Consumed.as("name-sppliter"));

        final var personNameSplitterBranches = personDTOKStream
                .split(Named.as("kstream-person-"))
                .branch( (key, value) -> value.getName().getIsCompuesto(), Branched.as("compuesto"))
                .defaultBranch(Branched.as("simple"));

        final var kstreamNameCompuesto = personNameSplitterBranches.get("kstream-person-compuesto");
        kstreamNameCompuesto.to("person-topic-compuesto");

        final var kstreamNameSimple = personNameSplitterBranches.get("kstream-person-simple");
        kstreamNameSimple.to("person-topic-simple");

        return personDTOKStream;
    }
}

