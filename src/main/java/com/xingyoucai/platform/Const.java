package com.xingyoucai.platform;

public class Const {

	public static final String SCHEME = "http";

	public static final String HOST = "wkb-qiye.hyh.xingyoucai.com";//w-ssl.xingyoucai.com";
	
	public static final String MEMBERSTATUS = SCHEME + "://" + HOST + "/apiuser/getMemberStatus";

	public static final String SIGNKEY = SCHEME + "://" + HOST + "/apiuser/getSignKey";

	public static final String LEVEL = SCHEME + "://" + HOST + "/apiuser/changeLevel";

	public static final String REGISTER = SCHEME + "://" + HOST + "/apiuser/register";
	
	public static final String LOGIN = SCHEME + "://" + HOST + "/passport/login";
}
