package com.gumi.cursos.kstream.namesplitter.topology.namesplitter.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.context.annotation.Bean;

public class NameSplitterTopologyTestConfig {

	@Bean
	public StreamsBuilder streamsBuilder(){
		return new StreamsBuilder();
	}
}
