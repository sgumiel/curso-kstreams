package com.gumi.cursos.kstream.namesplitter.topology.namesplitter.filter;

import com.gumi.cursos.kstream.namesplitter.model.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonWithNameComposed implements Predicate<String, Person> {

	@Override
	public boolean test(String s, Person person) {

		log.debug("Test person with key: {}", s);

		final var nameComposed = person.getName().getIsCompuesto();
		log.debug("Name composed: {}", nameComposed);

		return nameComposed;
	}
}
