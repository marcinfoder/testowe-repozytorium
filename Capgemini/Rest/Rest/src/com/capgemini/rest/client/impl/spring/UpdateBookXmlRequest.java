package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.BookBuilder;
import com.capgemini.rest.client.RequestUtils;

@Service("updateXmlBook")
public class UpdateBookXmlRequest extends UpdateBookRequest {

	public UpdateBookXmlRequest() {
		super(MediaType.APPLICATION_XML, RequestUtils.URL_BOOK);
	}

	@Override
	protected Book createBook() {
		return new BookBuilder().withId(5L).withTitle("Stary czlowiek i morze")
				.withAuthor("Ernest H").build();
	}
}
