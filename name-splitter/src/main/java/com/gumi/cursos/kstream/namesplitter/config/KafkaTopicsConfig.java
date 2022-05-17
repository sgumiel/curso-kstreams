package com.gumi.cursos.kstream.namesplitter.config;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Data
@Configuration
public class KafkaTopicsConfig {

	@Autowired
	private KafkaTopicProperties kafkaTopicProperties;

	@Bean
	public NewTopic nameComposedTopic(){
		return TopicBuilder.name(this.kafkaTopicProperties.getNameComposed())
				.partitions(1)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic nameSimpleTopic(){
		return TopicBuilder.name(this.kafkaTopicProperties.getNameSimple())
				.partitions(1)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic loginTopic(){
		return TopicBuilder.name(this.kafkaTopicProperties.getLogin())
				.partitions(1)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic otherStreamBuilderTopic(){
		return TopicBuilder.name(this.kafkaTopicProperties.getOtherStreamBuilder())
				.partitions(1)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic personTopic(){
		return TopicBuilder.name(this.kafkaTopicProperties.getPerson())
				.partitions(1)
				.replicas(1)
				.build();
	}
}
