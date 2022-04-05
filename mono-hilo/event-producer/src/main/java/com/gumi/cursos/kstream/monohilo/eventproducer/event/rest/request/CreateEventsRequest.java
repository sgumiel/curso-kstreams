package com.gumi.cursos.kstream.monohilo.eventproducer.event.rest.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventsRequest implements Serializable {

	private int numberOfEvents;
	private int max;
	private int min;
}
