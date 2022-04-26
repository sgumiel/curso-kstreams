package com.gumi.cursos.kstream.monohilo.eventconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SleepService {

	void sleep(long milis) throws InterruptedException {
		Thread.sleep(milis);
	}
}
