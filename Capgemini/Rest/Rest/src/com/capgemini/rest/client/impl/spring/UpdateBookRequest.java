package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import com.capgemini.persistence.domain.Book;
import com.capgemini.rest.client.RequestUtils;

public abstract class UpdateBookRequest extends AbstractRequest {

	private final MediaType contentType;

	public UpdateBookRequest(MediaType contentType, String url) {
		super(RequestUtils.URL_BOOK);
		this.contentType = contentType;
	}

	@Override
	public void execute() throws Exception {
		HttpEntity<Book> entity = prepareEntity(createBook(), contentType);
		restTemplate.put(requestUtils.getUrl(url), entity);
	}

	protected abstract Book createBook();

}
