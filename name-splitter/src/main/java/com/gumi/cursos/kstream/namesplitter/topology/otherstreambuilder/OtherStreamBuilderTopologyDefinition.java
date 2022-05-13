package com.gumi.cursos.kstream.namesplitter.topology.otherstreambuilder;

import static com.gumi.cursos.kstream.namesplitter.topology.otherstreambuilder.constant.OtherStreamBuilderTopologyConstant.CONSUMED_AS_OTHER_STREAM_BUILDER_CONSUMER;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.OtherStreamBuilderDTO;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OtherStreamBuilderTopologyDefinition {

	@Bean
	public Topology otherStreamBuilderTopology(
			final KafkaTopicProperties kafkaTopicProperties) {


		final StreamsBuilder streamsBuilder = new StreamsBuilder();
		final var otherStreamBuilder = streamsBuilder
				.<String, OtherStreamBuilderDTO>stream(kafkaTopicProperties.getOtherStreamBuilder(), Consumed.as(CONSUMED_AS_OTHER_STREAM_BUILDER_CONSUMER));

		otherStreamBuilder
				.peek((key, value) -> log.debug("Other stream builder. Key: {}, value: {}", key, value));

		return streamsBuilder.build();
	}
}