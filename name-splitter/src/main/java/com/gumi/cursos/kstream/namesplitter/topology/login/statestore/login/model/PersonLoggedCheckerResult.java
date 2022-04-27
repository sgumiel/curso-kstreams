package com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.model;

import com.gumi.cursos.kstream.infrastructure.kafka.avro.PersonDTO;
import lombok.Getter;

@Getter
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
