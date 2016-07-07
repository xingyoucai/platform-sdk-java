package com.xingyoucai.sample;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.xingyoucai.platform.CallBack;
import com.xingyoucai.platform.QmxSdk;

public class QmxSdkTest {

	private QmxSdk sdk;

	@Before
	public void init() {
		String APP_ID = "wkb000111";
		String APP_SECRET = "1f3lul1gnpkflwriaue05p5quwysgzv0";
		sdk = new QmxSdk(APP_ID, APP_SECRET);
	}
	
	@Test
	public void register() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_name", "fushizhe");
		map.put("mobile", "15110068969");
		map.put("user_id", "196");
		map.put("level", "1");
		map.put("member_extends", URLEncoder.encode(new Gson().toJson(new Result(10))));// json
		sdk.register(map,  new CallBack() {

			public void success(Response response) {
				try {
					System.out.println(response.body().string());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void fail() {
				
			}
		});
	}

	public void changeLevel() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("level", "1");
		sdk.register(map, new CallBack() {

			public void success(Response response) {
				try {
					System.out.println(response.body().string());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void fail() {

			}
		});
	}

	
	public void getMemberStatus() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "15110067060");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		sdk.getMemberStatus(map, new CallBack() {

			public void success(Response response) {
				try {
					System.out.println(response.body().string());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void fail() {

			}
		});
	}

	public void getSignKey() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", "18652176511");
		map.put("_time", String.valueOf(System.currentTimeMillis()));
		sdk.getMemberStatus(map, new CallBack() {

			public void success(Response response) {
				try {
					System.out.println(response.body().string());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void fail() {

			}
		});
	}
}
