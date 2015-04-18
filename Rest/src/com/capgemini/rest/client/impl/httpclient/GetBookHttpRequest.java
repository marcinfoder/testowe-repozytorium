package com.capgemini.rest.client.impl.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.http.MediaType;

import com.capgemini.rest.client.RequestUtils;

public abstract class GetBookHttpRequest extends HttpRequest {

	public GetBookHttpRequest(MediaType acceptedMediaType) {
		super(acceptedMediaType, RequestUtils.URL_BOOK);
	}

	@Override
	protected HttpRequestBase createRequest() {
		return new HttpGet(requestUtils.getUrlWithValue(url, 1L));
	}

}
