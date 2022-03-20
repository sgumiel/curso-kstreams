package com.gumi.cursos.kstream.namesplitter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "topics")
public class KafkaTopicProperties {

	private String person;
	private String nameComposed;
	private String nameSimple;
	private String login;

}