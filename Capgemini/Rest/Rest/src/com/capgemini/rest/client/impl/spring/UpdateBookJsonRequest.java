package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.BookBuilder;
import com.capgemini.rest.client.RequestUtils;

@Service("updateJsonBook")
public class UpdateBookJsonRequest extends UpdateBookRequest {

	public UpdateBookJsonRequest() {
		super(MediaType.APPLICATION_JSON, RequestUtils.URL_BOOK);
	}

	@Override
	protected Book createBook() {
		return new BookBuilder().withId(5L).withTitle("W pustyni i w puszczy")
				.withAuthor("Henryk Sienkiewicz").build();
	}

}
