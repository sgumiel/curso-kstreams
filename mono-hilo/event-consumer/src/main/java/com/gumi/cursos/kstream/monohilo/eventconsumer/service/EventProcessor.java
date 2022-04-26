package com.gumi.cursos.kstream.monohilo.eventconsumer.service;

import java.time.LocalTime;

import com.gumi.cursos.kstream.monohilo.eventconsumer.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventProcessor {

	private final SleepService sleepService;

	public void processEvent(Event event) throws InterruptedException {
		log.debug("Now: [{}]. Event: [{}]", LocalTime.now(), event);

		this.sleepService.sleep(event.getMilisToSleep());
		log.debug("Now: [{}]. Event processed", LocalTime.now());

	}
}