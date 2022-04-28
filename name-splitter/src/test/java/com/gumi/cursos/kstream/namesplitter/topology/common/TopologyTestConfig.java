package com.gumi.cursos.kstream.namesplitter.topology.common;

import com.gumi.cursos.kstream.namesplitter.config.ApplicationProperties;
import com.gumi.cursos.kstream.namesplitter.config.KafkaStreamsConfig;
import com.gumi.cursos.kstream.namesplitter.config.KafkaTopicProperties;
import com.gumi.cursos.kstream.namesplitter.statestore.StateStoreConfig;
import com.gumi.cursos.kstream.namesplitter.topology.common.BaseTopologyConfig;
import org.springframework.context.annotation.Import;

@Import({
		KafkaTopicProperties.class,
		BaseTopologyConfig.class,
		ApplicationProperties.class,
		KafkaStreamsConfig.class,
		StateStoreConfig.class })
public class TopologyTestConfig {
}