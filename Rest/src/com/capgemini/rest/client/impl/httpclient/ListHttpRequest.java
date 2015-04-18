package com.capgemini.rest.client.impl.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.http.MediaType;

public class ListHttpRequest extends HttpRequest {

	public ListHttpRequest(MediaType accessedMediaType) {
		super(accessedMediaType);
	}

	public ListHttpRequest(MediaType accessedMediaType, String url) {
		super(accessedMediaType, url);
	}

	protected HttpRequestBase createRequest() {
		return new HttpGet(requestUtils.getUrl(url));
	}

}
