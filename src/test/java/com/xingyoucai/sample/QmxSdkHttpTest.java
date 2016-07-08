package com.xingyoucai.sample;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.xingyoucai.platform.QmxSdkHttp;

public class QmxSdkHttpTest {
	
	private QmxSdkHttp sdk;
	
	@Before
	public void init() {
		String APP_ID = "wkb000111";
		String APP_SECRET = "1f3lul1gnpkflwriaue05p5quwysgzv0";
		sdk = new QmxSdkHttp(APP_ID, APP_SECRET);
	}
	
	@Test
	public void getMemberStatus() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		String res = sdk.getMemberStatus(map);
		System.out.println(res);
	}
	
	@Test
	public void register() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_name", "fushizhe");
		map.put("mobile", "18652176511");
		map.put("user_id", "1960");
		map.put("level", "1");
		String res = sdk.register(map);
		System.out.println(res);
	}
	
	@Test
	public void changeLevel() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("user_id", "1960");
		map.put("level", "3");
		String res=sdk.changeLevel(map);
		System.out.println(res);
	}
 
	@Test
	public void getSignKey() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		String res=sdk.getMemberStatus(map);
		System.out.println(res);
	}
}
