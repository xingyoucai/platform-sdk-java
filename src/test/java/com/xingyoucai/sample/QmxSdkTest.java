package com.xingyoucai.sample;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.xingyoucai.platform.Const;
import com.xingyoucai.platform.QmxSdk;

public class QmxSdkTest {
	
	private QmxSdk sdk;
	
	@Before
	public void init() throws Exception {
		String APP_ID = "wkb0000";
		String APP_SECRET = "u7alul1gnpk2dfgiaue05p5quwysgg8k";
		sdk = new QmxSdk(APP_ID, APP_SECRET);
		sdk.setDebugEnable(true);
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
		
		//
		boolean status=false;
		Cookie[] cookies= sdk.getHttpClient().getState().getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().contains(Const.LOGIN_COOKIE)){
				status=true;
				break;
			}
		}
		sdk.getLoger().debug(status?"login success":"login failed");
	}

	public void getMemberStatus() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		String res = sdk.getMemberStatus(map);
	}
	
	public void register() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_name", "fushizhe");
		map.put("mobile", "18652176511");
		map.put("user_id", "1961");
		map.put("level", "1");
		String res = sdk.register(map);
	}
	
	public void changeLevel() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("user_id", "1960");
		map.put("level", "3");
		String res=sdk.changeLevel(map);
	}
 
}