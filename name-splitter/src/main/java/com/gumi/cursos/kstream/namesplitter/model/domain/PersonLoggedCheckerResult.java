package com.gumi.cursos.kstream.namesplitter.model.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonLoggedCheckerResult {

	private Person person;
	private boolean logged;

	private PersonLoggedCheckerResult(Person person, boolean logged){
		this.person = person;
		this.logged = logged;
	}

	public static PersonLoggedCheckerResult of(Person person, boolean exists){
		return new PersonLoggedCheckerResult(person, exists);
	}
}
