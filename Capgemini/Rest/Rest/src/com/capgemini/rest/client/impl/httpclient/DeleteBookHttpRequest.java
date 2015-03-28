package com.capgemini.rest.client.impl.httpclient;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.http.MediaType;

import com.capgemini.rest.client.RequestUtils;

public abstract class DeleteBookHttpRequest extends HttpRequest {

	private final Long bookToDelete;

	public DeleteBookHttpRequest(MediaType acceptedMediaType, Long bookToDelete) {
		super(acceptedMediaType, RequestUtils.URL_BOOK);
		this.bookToDelete = bookToDelete;
	}

	@Override
	protected HttpRequestBase createRequest() {
		return new HttpDelete(requestUtils.getUrlWithValue(url, bookToDelete));
	}

}
