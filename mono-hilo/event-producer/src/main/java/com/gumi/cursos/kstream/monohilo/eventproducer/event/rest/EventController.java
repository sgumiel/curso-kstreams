package com.gumi.cursos.kstream.monohilo.eventproducer.event.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.gumi.cursos.kstream.monohilo.eventproducer.event.Event;
import com.gumi.cursos.kstream.monohilo.eventproducer.event.kafka.EventProducer;
import com.gumi.cursos.kstream.monohilo.eventproducer.event.rest.request.CreateEventsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {

	private final EventProducer eventProducer;

	@PostMapping
	public List<Event> createEvents(@RequestBody final CreateEventsRequest createEventsRequest) {

		final List<Event> events = IntStream.range(0, createEventsRequest.getNumberOfEvents())
				.mapToObj( i -> this.builder(createEventsRequest.getMin(), createEventsRequest.getMax()))
				.collect(Collectors.toList());

		this.eventProducer.publish(events);

		return events;

	}

	private Event builder(final int min, final int max) {

		final int milisToSleep = (int)(Math.random()*(3000-1000)+1000);
		final int number = (int)(Math.random()*(max-min+1)+min);
		return  Event.builder()
				.milisToSleep(milisToSleep)
				.number(number).build();

	}
}
