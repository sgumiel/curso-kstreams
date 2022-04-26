package com.gumi.cursos.kstream.monohilo.eventconsumer.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gumi.cursos.kstream.monohilo.eventconsumer.event.Event;
import com.gumi.cursos.kstream.monohilo.eventconsumer.service.EventProcessor;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventConsumer {

	private final EventProcessor eventProcessor;

	@KafkaListener(topics = "${topics.event}")
	public void peliculaListener(@Payload Event event) throws JsonProcessingException, InterruptedException {

		System.out.println("Event received: " + event);

        this.eventProcessor.processEvent(event);
	}
}
