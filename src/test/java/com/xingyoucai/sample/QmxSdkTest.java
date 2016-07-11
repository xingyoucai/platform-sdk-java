package com.xingyoucai.sample;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.xingyoucai.platform.QmxSdk;

public class QmxSdkTest {
	
	private QmxSdk sdk;
	
	@Before
	public void init() throws Exception {
		String APP_ID = "wkb000111";
		String APP_SECRET = "1f3lul1gnpkflwriaue05p5quwysgzv0";
		sdk = new QmxSdk(APP_ID, APP_SECRET);
	}
	
	@Test
	public void login() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", "18652176511");
		params.put("_time", String.valueOf(System.currentTimeMillis()));
		String json=sdk.getSignKey(params);
		Result result=new Gson().fromJson(json, Result.class);
		if(result==null)return;
		if(result.getErrcode()!=0){
			System.out.println(result.getErrmsg());
			return;
		}
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("signKey", result.getData().getSignKey());
		paramsMap.put("return_url","https://xingyoucai.com" );
		sdk.login(paramsMap);

	}
	
	public void getMemberStatus() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		String res = sdk.getMemberStatus(map);
		System.out.println(res);
	}
	
	public void register() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_name", "fushizhe");
		map.put("mobile", "18652176511");
		map.put("user_id", "1961");
		map.put("level", "1");
		String res = sdk.register(map);
		System.out.println(res);
	}
	
	public void changeLevel() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("user_id", "1960");
		map.put("level", "3");
		String res=sdk.changeLevel(map);
		System.out.println(res);
	}
 
}