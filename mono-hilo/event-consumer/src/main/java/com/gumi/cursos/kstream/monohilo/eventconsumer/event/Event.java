package com.gumi.cursos.kstream.monohilo.eventconsumer.event;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event implements Serializable {

	private int milisToSleep;
	private int number;
}
