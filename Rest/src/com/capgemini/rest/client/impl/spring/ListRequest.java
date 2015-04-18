package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.capgemini.persistence.domain.Books;
import com.capgemini.rest.client.RequestUtils;

public abstract class ListRequest extends AbstractRequest {

	public ListRequest(MediaType accessedMediaType) {
		super(accessedMediaType, RequestUtils.URL_LIST);
	}

	public ListRequest(MediaType accessedMediaType, String url) {
		super(accessedMediaType, url);
	}

	public void execute() {
		HttpEntity<String> entity = prepareEntity();
		ResponseEntity<Books> response = restTemplate.exchange(
				requestUtils.getUrl(url), HttpMethod.GET, entity, Books.class);

		//Books books = restTemplate.getForObject(
		//		requestUtils.getUrl(url), Books.class);
		//this method sets accept header to the all available values from converters
		//in this case application/xml and application/json
		//spring knows, which converters can be used for the response type class (Books)

		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		Books books = response.getBody();
		System.out.println(books);
	}
}
