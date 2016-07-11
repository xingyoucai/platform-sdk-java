package com.xingyoucai.platform;

public class Const {

	public static final String SCHEME = "https";

	public static final String HOST = "w-ssl.xingyoucai.com";
	
	public static final String MEMBERSTATUS = SCHEME + "://" + HOST + "/apiuser/getMemberStatus";

	public static final String SIGNKEY = SCHEME + "://" + HOST + "/apiuser/getSignKey";

	public static final String LEVEL = SCHEME + "://" + HOST + "/apiuser/changeLevel";

	public static final String REGISTER = SCHEME + "://" + HOST + "/apiuser/register";
	
	public static final String LOGIN = SCHEME + "://" + HOST + "/passport/login";
	
	public static final String LOGIN_COOKIE="TRADEVESTING_PASSPORT_MEMBER_";
}
