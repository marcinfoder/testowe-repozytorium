package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.capgemini.persistence.domain.Book;
import com.capgemini.rest.client.RequestUtils;

public abstract class GetBookRequest extends AbstractRequest {

	public GetBookRequest(MediaType acceptedMediaType) {
		super(acceptedMediaType, RequestUtils.URL_BOOK);
	}
	
	@Override
	public void execute() throws Exception {
		HttpEntity<String> entity = prepareEntity();
		ResponseEntity<Book> response = restTemplate.exchange(
				requestUtils.getUrlWithId(url), HttpMethod.GET, entity, Book.class, 1L);

		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		Book book = response.getBody();
		System.out.println(book);
	}
	
}
