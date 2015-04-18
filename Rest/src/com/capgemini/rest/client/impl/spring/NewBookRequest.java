package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.capgemini.persistence.domain.Book;

public abstract class NewBookRequest<Type> extends AbstractRequest {

	private final MediaType contentType;

	public NewBookRequest(MediaType acceptedMediaType, MediaType contentType, String url) {
		super(acceptedMediaType, url);
		this.contentType = contentType;
	}

	public void execute() {
		Type newBook = createBook();
		HttpEntity<Type> entity = prepareEntity(newBook, contentType);

		ResponseEntity<Book> response = restTemplate.postForEntity(
				requestUtils.getUrl(url), entity, Book.class);

		//Book book = restTemplate.postForObject(
		//		requestUtils.getUrl(url), newBook, Book.class);
		//in this case spring sets the value of request content-type to the first allowed media type value from converter
		//2 values (and converters) are allowed for Book type - xml and json, the content-type header depends on the order of converters in restTeplate object
		//
		//moreover this method sets accept header to the all allowed values for Book object from converters
		//in this case application/xml and application/json
		//spring knows, which converters can be used for the response type class (Book)
		//
		//
		//it is important to set the content-type header value for the request, if restTemplate has a lot of different converters and
		//a called controller (rest service) accepts the one specific content-type, for example:
		//if controller accepts only json content-type and restTemplate has json- and xml-converters, then the content-type header value
		//should be set for request, because it is a high risk, that the request body will be sent in xml format
		//
		//if Accept is null (and contentType is not null) then Accept = all allowed for rest template and this object
		//if contentType is null (and Accept is not null) then contentType = first allowed media type (converter) from template

		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		Book book = response.getBody();
		System.out.println(book);
	}

	protected abstract Type createBook();
}