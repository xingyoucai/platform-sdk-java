package com.xingyoucai.platform;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

public class QmxSdkHttp {

	private String APP_ID, APP_SECRET;

	private HttpClient httpClient;

	public QmxSdkHttp(String app_id, String app_secret) {
		this.APP_ID = app_id;
		this.APP_SECRET = app_secret;
	}

	/**
	 * 获取用户注册状态
	 * 
	 * @throws Exception
	 */
	public String getMemberStatus(Map<String, String> paramsMap) throws Exception {
		return request(Const.MEMBERSTATUS, paramsMap);
	}

	/**
	 * 获取登录加密串
	 * 
	 * @throws Exception
	 */
	public String getSignKey(Map<String, String> paramsMap) throws Exception {
		return request(Const.SIGNKEY, paramsMap);
	}

	/**
	 * 注册
	 * 
	 * @throws Exception
	 */
	public String register(Map<String, String> paramsMap) throws Exception {
		return request(Const.REGISTER, paramsMap);
	}

	/**
	 * 修改用户等级
	 * 
	 * @throws Exception
	 */
	public String changeLevel(Map<String, String> paramsMap) throws Exception {
		return request(Const.LEVEL, paramsMap);
	}

	private String request(String url, Map<String, String> paramsMap) throws Exception {
		signData(paramsMap);
		NameValuePair[] pairs = new NameValuePair[paramsMap.size()];
		int i = 0;
		for (String k : paramsMap.keySet()) {
			pairs[i] = new NameValuePair(k, paramsMap.get(k));
			i++;
		}
		PostMethod post = new PostMethod(url);
		post.setRequestBody(pairs);
		getHttpClient().executeMethod(post);
		String res = post.getResponseBodyAsString();
		post.releaseConnection();
		return res;
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

	private HttpClient getHttpClient() throws GeneralSecurityException, IOException {
		if (httpClient == null) {
			Protocol protocol = new Protocol(Const.HTTPS, new EasySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol(Const.HTTPS, protocol);

			httpClient = new HttpClient();
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			
			HostConfiguration hostConfiguration = new HostConfiguration();
			hostConfiguration.setHost(Const.HOST, 80, protocol);
			httpClient.setHostConfiguration(hostConfiguration);
		}
		return httpClient;
	}
}
