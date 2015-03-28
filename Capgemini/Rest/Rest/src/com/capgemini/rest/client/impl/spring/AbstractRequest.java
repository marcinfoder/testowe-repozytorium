package com.capgemini.rest.client.impl.spring;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.capgemini.rest.client.IRequest;
import com.capgemini.rest.client.RequestUtils;

public abstract class AbstractRequest implements IRequest {

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected RequestUtils requestUtils;

	protected final String url;
	protected final MediaType acceptedMediaType;

	public AbstractRequest(String url) {
		this(null, url);
	}

	public AbstractRequest(MediaType acceptedMediaType, String url) {
		this.acceptedMediaType = acceptedMediaType;
		this.url = url;
	}

	protected <T> HttpEntity<T> prepareEntity() {
		return prepareEntity(null);
	}

	protected <T> HttpEntity<T> prepareEntity(T object, MediaType contentType) {
		HttpHeaders headers = createHeaders(contentType);
		HttpEntity<T> entity = new HttpEntity<T>(object, headers);
		return entity;
	}

	protected <T> HttpEntity<T> prepareEntity(MediaType contentType) {
		HttpHeaders headers = createHeaders(contentType);
		HttpEntity<T> entity = new HttpEntity<T>(headers);
		return entity;
	}

	private HttpHeaders createHeaders(MediaType contentType) {
		HttpHeaders headers = new HttpHeaders();
		if (contentType != null) {
			headers.setContentType(contentType);
		}
		if (acceptedMediaType != null) {
			headers.setAccept(Collections.singletonList(acceptedMediaType));
		}
		return headers;
	}

}
