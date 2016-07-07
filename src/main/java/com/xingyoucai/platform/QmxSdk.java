package com.xingyoucai.platform;

import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base64;

public class QmxSdk {

	private String APP_ID;
	private String APP_SECRET;

	public QmxSdk(String app_id, String app_secret) {
		this.APP_ID = app_id;
		this.APP_SECRET = app_secret;
	}

	/**
	 * 获取用户注册状态
	 * 
	 * @throws Exception
	 */
	public void getMemberStatus( Map<String, String> paramsMap, CallBack callBack) throws Exception {
		request(Const.memberstatus, paramsMap, callBack);
	}

	/**
	 * 获取登录加密串
	 * 
	 * @throws Exception
	 */
	public void getSignKey( Map<String, String> paramsMap, CallBack callBack) throws Exception {
		request(Const.signkey, paramsMap, callBack);
	}

	/**
	 * 注册
	 * 
	 * @throws Exception
	 */
	public void register( Map<String, String> paramsMap, CallBack callBack) throws Exception {
		request(Const.register, paramsMap, callBack);
	}

	/**
	 * 修改用户等级
	 * 
	 * @throws Exception
	 */
	public void changeLevel( Map<String, String> paramsMap, CallBack callBack) throws Exception {
		request(Const.level, paramsMap, callBack);
	}
	
	private void request(String url, Map<String, String> paramsMap, CallBack callBack) {
		if(callBack==null)
			throw new NullPointerException("CallBack cannot be null");
		if(paramsMap==null)
			throw new NullPointerException("Params cannot be null");
		try {
			signData(paramsMap);	
			Builder builder = new FormBody.Builder();
			for (String k : paramsMap.keySet())
				builder.add(k, paramsMap.get(k));
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).post(builder.build()).build();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				callBack.success(response);
			} else {
				callBack.fail();
			}
		} catch (Exception e) {
			callBack.fail();
		}
	}

	private Map<String, String> signData(Map<String, String> map) throws Exception {
		map.put("appid", APP_ID);
		String plaintext = httpBuildQuery(kSort(map));
		String cipherText = hashHmac(plaintext, APP_SECRET);
		map.put("sign", URLEncoder.encode(cipherText));
		return map;
	}

	private Map<String, String> kSort(Map<String, String> data) {
		Map<String, String> treeMap = new TreeMap(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeMap.putAll(data);
		return treeMap;
	}

	private String httpBuildQuery(Map<String, String> data) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String key : data.keySet()) {
			i++;
			sb.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(data.get(key)));
			if (i < data.keySet().size())
				sb.append("&");
		}
		return sb.toString();
	}

	private String hashHmac(String data, String secret) throws Exception {
		String HMAC_SHA256 = "HmacSHA256";
		Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256);
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
		sha256_HMAC.init(secret_key);
		return Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
	}

}
