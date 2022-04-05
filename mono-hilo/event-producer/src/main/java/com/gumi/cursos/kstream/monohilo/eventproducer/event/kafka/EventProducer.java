package com.gumi.cursos.kstream.monohilo.eventproducer.event.kafka;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gumi.cursos.kstream.monohilo.eventproducer.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public void publish(final List<Event> events) {

		events.stream()
				.forEach(event -> {
					try {
						this.kafkaTemplate.send("events-topic", this.objectMapper.writeValueAsString(event));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});
	}
}
