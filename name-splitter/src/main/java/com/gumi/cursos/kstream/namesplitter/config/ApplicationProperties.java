package com.gumi.cursos.kstream.namesplitter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationProperties {

	@Value("${spring.kafka.bootstrap-servers}") private String bootstrapAddress;
	@Value("${spring.application.name}") private String applicationName;
	@Value("${schema.registry.url}") private String schemaRegistryUrl;
}
