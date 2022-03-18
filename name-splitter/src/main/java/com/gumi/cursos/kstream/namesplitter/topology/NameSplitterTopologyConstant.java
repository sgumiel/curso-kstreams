package com.gumi.cursos.kstream.namesplitter.topology;

public abstract class NameSplitterTopologyConstant {

	public static final String TOPIC_IN_PERSON_TOPIC = "person-topic";
	public static final String PERSON_TOPIC_CONSUMED_AS = "name-splitter";
	public static final String PERSON_TOPIC_SPLIT_AS = "kstream-person-";
	public static final String PERSON_TOPIC_BRANCH_COMPUESTO = "compuesto";
	public static final String PERSON_TOPIC_BRANCH_SIMPLE = "simple";
	public static final String PERSON_TOPIC_KSTREAM_COMPUESTO = PERSON_TOPIC_SPLIT_AS + PERSON_TOPIC_BRANCH_COMPUESTO;
	public static final String PERSON_TOPIC_KSTREAM_SIMPLE = PERSON_TOPIC_SPLIT_AS + PERSON_TOPIC_BRANCH_SIMPLE;
	public static final String TOPIC_OUT_PERSON_COMPUESTO_TOPIC = "person-compuesto-topic";
	public static final String TOPIC_OUT_PERSON_SIMPLE_TOPIC = "person-simple-topic";
}
