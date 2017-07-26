package com.gosun.isap.face.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 与人脸系统信息交互过程中用到的方法<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class IGFSCommunication {
	/**
	 * 模拟get请求
	 * 
	 * @param url
	 *            请求URL
	 * @return 请求结果
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String get(String url) throws ParseException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", "application/json");
		body = invoke(httpclient, get);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	/**
	 * 模拟post请求
	 * 
	 * @param url
	 *            请求URL
	 * @param jsonString
	 *            json序列化后的String
	 * @return 请求结果
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String post(String url, String jsonString) throws ParseException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonString, "utf-8");
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/json");
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost)
			throws ParseException, IOException {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		response = httpclient.execute(httpost);
		return response;
	}

	private static String paseResponse(HttpResponse response) throws ParseException, IOException {
		HttpEntity entity = response.getEntity();
		// String charset = EntityUtils.getContentCharSet(entity);
		String body = null;
		body = EntityUtils.toString(entity);
		return body;
	}
}
