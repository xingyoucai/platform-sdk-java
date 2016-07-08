package com.xingyoucai.sample;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.xingyoucai.platform.QmxSdk;

public class QmxSdkTest {
	
	private QmxSdk sdk;
	
	private String APP_ID = "wkb0001", APP_SECRET = "u7alul1gnpk2swriaue05p5quwysgg8k";
	
	@Before
	public void init() throws Exception {
		sdk = new QmxSdk(APP_ID, APP_SECRET);
	}
	
	@Test
	public void login() throws Exception {
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("mobile", "18652176511");
		map1.put("_time", String.valueOf(System.currentTimeMillis()));
		String json=sdk.getSignKey(map1);
		System.out.println(json);
		Result result=new Gson().fromJson(json, Result.class);
		if(result==null)return;
		if(result.getErrcode()!=0){
			System.out.println(result.getErrmsg());
			return;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", APP_ID);
		map.put("signKey", result.getData().getSignKey());
		map.put("return_url", "https://xingyoucai.com");
		String res = sdk.login(map);
		System.out.println(res);

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
		map.put("user_id", "1961");
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
 
}