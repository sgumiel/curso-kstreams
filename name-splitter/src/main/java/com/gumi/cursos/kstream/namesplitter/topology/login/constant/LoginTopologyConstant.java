package com.gumi.cursos.kstream.namesplitter.topology.login.constant;

public abstract class LoginTopologyConstant {

	public static final String CONSUMED_AS_LOGIN_CONSUMER = "login-consumer";
	public static final String PERSON_LOGIN_STORE = "person-login-store";
	public static final String SPLIT_NAMED_AS = "person-login-";
	public static final String BRANCHED_AS_ALREADY_LOGGED = "already-logged";
	public static final String BRANCHED_AS_NOT_LOGGED = "not-logged";

	public static final String BRANCH_ALREADY_LOGGED = SPLIT_NAMED_AS + BRANCHED_AS_ALREADY_LOGGED;

	public static final String BRANCH_NOT_LOGGED = SPLIT_NAMED_AS + BRANCHED_AS_NOT_LOGGED;


}
