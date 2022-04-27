package com.gumi.cursos.kstream.namesplitter.topology.login.subtopology;

import com.gumi.cursos.kstream.namesplitter.topology.login.statestore.login.model.PersonLoggedCheckerResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;

@Slf4j
public abstract class PersonAlreadyLoggedSubTopologyDefinition {

	public static void personAlreadyLoggedBranch(KStream<String, PersonLoggedCheckerResult> kStream) {

		kStream
				.peek((key, person) -> log.debug("[key: {}] -> Person already logged", key));

	}
}
