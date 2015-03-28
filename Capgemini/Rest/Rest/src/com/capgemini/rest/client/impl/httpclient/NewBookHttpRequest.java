package com.capgemini.rest.client.impl.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.http.MediaType;

public abstract class NewBookHttpRequest extends HttpRequest {

	private final MediaType contentType;

	public NewBookHttpRequest(MediaType acceptedMediaType, MediaType contentType, String url) {
		super(acceptedMediaType, url);
		this.contentType = contentType;
	}

	@Override
	protected HttpRequestBase createRequest() {
		HttpPost request = new HttpPost(requestUtils.getUrl(url));
		HttpEntity value = new StringEntity(
				createBook(),
				ContentType.create(contentType.toString(),
						"UTF-8"));
		request.setEntity(value);
		return request;
	}

	protected abstract String createBook();

}