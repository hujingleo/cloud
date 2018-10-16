package com.suyou.eurekaclient.utils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.client.http.HttpResponse;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

public class HttpClientUtil {

	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 通过Get方法调用第三方接口
	 *
	 * @param url
	 * @return
	 */
	public static String getGetResponse(String url, Map<String, String> headerMap) {
		String responseContent = null;

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(2000); // 超时时间
		params.setSoTimeout(2000);
		params.setMaxTotalConnections(500); // 最大连接数
		params.setDefaultMaxConnectionsPerHost(500);
		params.setStaleCheckingEnabled(true);

		SimpleHttpConnectionManager simpleConnectionManage = new SimpleHttpConnectionManager();
		simpleConnectionManage.setParams(params); // 设置参数

		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));// 连接不上
																													// 重试次数

		HttpClient httpClient = new HttpClient(simpleConnectionManage); // 构造HttpClient的实例
		httpClient.setParams(httpClientParams);

		GetMethod getMethod = new GetMethod(url); // 创建GET方法的实例

		if (null != headerMap && !headerMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getKey())) {
					getMethod.setRequestHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); // 使用系统提供的默认的恢复策略
		try {

			int statusCode = httpClient.executeMethod(getMethod); // 执行调用
			if (statusCode == HttpStatus.SC_OK) {
				responseContent = getMethod.getResponseBodyAsString();// 返回内容
			} else {
				log.error("Method failed: " + getMethod.getStatusLine());
			}

		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(); // 发生网络异常
			log.error(e.getMessage());
		} finally {
			getMethod.releaseConnection(); // 释放连接
		}
		return responseContent;
	}

	public static String getGetResponse(String url) {
		return getGetResponse(url, null);
	}

	public static String getPostResponse(String url, Map<String, String> paramMap) {
		return getPostResponse(url, paramMap, null);
	}

	/**
	 * 通过Post方法调用第三方接口
	 *
	 * @param url
	 * @param paramMap
	 *            添加的参数
	 * @return
	 */
	public static String getPostResponse(String url, Map<String, String> paramMap, Map<String, String> headerMap) {
		String responseContent = null;

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(10 * 1000); // 超时时间
		params.setSoTimeout(10 * 1000);
		params.setMaxTotalConnections(500); // 最大连接数
		params.setDefaultMaxConnectionsPerHost(500);
		params.setStaleCheckingEnabled(true);

		SimpleHttpConnectionManager simpleConnectionManage = new SimpleHttpConnectionManager();
		simpleConnectionManage.setParams(params); // 设置参数

		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));// 连接不上
																													// 重试次数

		HttpClient httpClient = new HttpClient(simpleConnectionManage); // 构造HttpClient的实例
		httpClient.setParams(httpClientParams);

		PostMethod postMethod = new PostMethod(url); // 创建POST方法的实例

		// 设置参数
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		if (null != headerMap && !headerMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getKey())) {
					postMethod.setRequestHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		if (paramMap != null) {
			for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				nameValuePairList.add(new NameValuePair(key, paramMap.get(key)));
			}
		}
		postMethod.setRequestBody(nameValuePairList.toArray(new NameValuePair[] {}));

		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); // 使用系统提供的默认的恢复策略
		try {

			int statusCode = httpClient.executeMethod(postMethod); // 执行调用
			if (statusCode == HttpStatus.SC_OK) {
				responseContent = postMethod.getResponseBodyAsString();// 返回内容
			} else {
				log.error("Method failed: " + postMethod.getStatusLine());
			}

		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(); // 发生网络异常
			log.error(e.getMessage());
		} finally {
			postMethod.releaseConnection(); // 释放连接
		}

		return responseContent;
	}

//	/**
//	 * post请求
//	 * @param url
//	 * @param json
//	 * @return
//	 */
//	public static JSONObject doPost(String url,JSONObject json){
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost(url);
//		JSONObject response = null;
//		try {
//			StringEntity s = new StringEntity(json.toString());
//			s.setContentEncoding("UTF-8");
//			s.setContentType("application/json");//发送json数据需要设置contentType
//			post.setEntity(s);
//			HttpResponse res = client.execute(post);
//			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//				HttpEntity entity = res.getEntity();
//				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
//				response = JSONObject.fromObject(result);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return response;
//	}
//
//public static String post(JSONObject json,String URL) {
//
//	HttpClient client = new DefaultHttpClient();
//	HttpPost post = new HttpPost(URL);
//	post.setHeader("Content-Type", "application/json");
//	post.addHeader("Authorization", "Basic YWRtaW46");
//	String result = "";
//
//	try {
//
//		StringEntity s = new StringEntity(json.toString(), "utf-8");
//		s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
//				"application/json"));
//		post.setEntity(s);
//
//		// 发送请求
//		HttpResponse httpResponse = client.execute(post);
//
//		// 获取响应输入流
//		InputStream inStream = httpResponse.getEntity().getContent();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(
//				inStream, "utf-8"));
//		StringBuilder strber = new StringBuilder();
//		String line = null;
//		while ((line = reader.readLine()) != null)
//			strber.append(line + "\n");
//		inStream.close();
//
//		result = strber.toString();
//		System.out.println(result);
//
//		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//
//			System.out.println("请求服务器成功，做相应处理");
//
//		} else {
//
//			System.out.println("请求服务端失败");
//
//		}
//
//
//	} catch (Exception e) {
//		System.out.println("请求异常");
//		throw new RuntimeException(e);
//	}
//
//	return result;
//}
public static  String sendJsonHttpPost(String url, String json) {

	CloseableHttpClient httpclient = HttpClients.createDefault();
	String responseInfo = null;
	try {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
		ContentType contentType = ContentType.create("application/json", CharsetUtils.get("UTF-8"));
		httpPost.setEntity(new StringEntity(json, contentType));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			if (null != entity) {
				responseInfo = EntityUtils.toString(entity);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return responseInfo;
}

	public  static String methodPost(String url,NameValuePair[] data){

		String response= "";//要返回的response信息
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		// 执行postMethod
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
				|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
			// 从头中取出转向的地址
			Header locationHeader = postMethod.getResponseHeader("location");
			String location = null;
			if (locationHeader != null) {
				location = locationHeader.getValue();
				System.out.println("The page was redirected to:" + location);
				response= methodPost(location,data);//用跳转后的页面重新请求。
			} else {
				System.err.println("Location field value is null.");
			}
		} else {
			System.out.println(postMethod.getStatusLine());

			try {
				response= postMethod.getResponseBodyAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			postMethod.releaseConnection();
		}
		return response;
	}
}
