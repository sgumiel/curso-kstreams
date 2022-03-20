package com.gumi.cursos.kstream.randomdata.config;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Data
@Configuration
public class KafkaTopicsConfig {

	@Value("${topics.person}")
	private String personTopic;

	@Bean
	public NewTopic personTopic(){
		return TopicBuilder.name(personTopic)
				.partitions(1)
				.replicas(1)
				.build();
	}
}
