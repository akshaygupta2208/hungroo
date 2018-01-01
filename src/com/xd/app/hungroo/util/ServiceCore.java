package com.xd.app.hungroo.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public class ServiceCore {

	
	
	
	
	public String postRequest(String url, String json) {
		// Map<String, String> paramMap = WebServiceClientInterceptor.getMap();
		System.out.println("In postRequest");
		String responseString = null;
		PostMethod method = null;
		try {
			//String domainUri = urlFormation.getOperationAndDetails(list);
			HttpClient client = new HttpClient();
			// Instantiate a GET HTTP method
			method = new PostMethod(url);
			method.addRequestHeader("content-type", "application/json");
			method.setRequestBody(json);
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			responseString = method.getResponseBodyAsString();
			System.out.println(method.getResponseBodyAsString());

			System.out.println("Response String ====" + responseString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// release connection
			method.releaseConnection();
		}

		return responseString;

	}

	public String getRequest(String url) {
		// Map<String, String> paramMap = WebServiceClientInterceptor.getMap();
		System.out.println("In getRequest");
		String responseString = null;
		GetMethod method = null;
		try {
			//String domainUri = urlFormation.getOperationAndDetails(list);
			// Instantiate an HttpClient
			HttpClient client = new HttpClient();

			// Instantiate a GET HTTP method
			method = new GetMethod(url);
			method.addRequestHeader("content-type", "application/json");
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			responseString = method.getResponseBodyAsString();
			System.out.println("Response String ====" + responseString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// release connection
			method.releaseConnection();
		}

		return responseString;

	}
}
