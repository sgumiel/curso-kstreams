package com.gumi.cursos.kstream.namesplitter.topology.common;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.context.annotation.Bean;

public class BaseTopologyConfig {

	@Bean
	public StreamsBuilder streamsBuilder(){
		return new StreamsBuilder();
	}
}
