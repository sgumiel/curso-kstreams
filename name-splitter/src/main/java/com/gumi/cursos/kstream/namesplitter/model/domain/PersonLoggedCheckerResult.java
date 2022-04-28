package com.gumi.cursos.kstream.namesplitter.model.domain;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonLoggedCheckerResult {

	private PersonDTO person;
	private boolean logged;

	private PersonLoggedCheckerResult(PersonDTO person, boolean logged){
		this.person = person;
		this.logged = logged;
	}

	public static PersonLoggedCheckerResult of(PersonDTO person, boolean exists){
		return new PersonLoggedCheckerResult(person, exists);
	}
}
