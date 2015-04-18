package com.capgemini.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpClientClient {

	public static void main(String[] args) throws Exception {

		String[] types = ImageIO.getReaderMIMETypes();
		for (String type : types) {
			System.out.println(type);
		}


		HttpClient httpclient = new DefaultHttpClient();

		HttpPost post = new HttpPost("http://localhost:8080/rest/service/emp");
		post.addHeader("Accept", "application/xml");
		HttpEntity value = new StringEntity("{\"id\":131,\"email\":\"andrzej.kaluza@capgemini.com\",\"specialName\":\"Andrew K\",\"image\":\"AQID\"}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(value);
		HttpResponse response1 = httpclient.execute(post);
		HttpEntity entity1 = response1.getEntity();
		if (entity1 != null) {
		    InputStream instream = entity1.getContent();
		    try {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		        System.out.println(reader.readLine());
		    } finally {
		        instream.close();
		    }
		}
		post.releaseConnection();


		HttpGet httpget = new HttpGet("http://localhost:8080/rest/service/employees");
		httpget.addHeader("Accept", "text/html");
		//httpget.addHeader("Accept", "application/xml");
		HttpResponse response = httpclient.execute(httpget);
		System.out.println("*");
		System.out.println(response.getFirstHeader("Content-Type"));
		System.out.println("*");
		HttpEntity entity = response.getEntity();
		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		        String line = null;
		        while((line = reader.readLine()) != null) {
		        	System.out.println(line);
		        }
		    } finally {
		        instream.close();
		    }
		}
		httpget.releaseConnection();
	}
}
