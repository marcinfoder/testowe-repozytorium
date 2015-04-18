package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.BookBuilder;
import com.capgemini.rest.client.RequestUtils;

@Service("newBookJson2Xml")
public class NewBookJsonToXmlRequest extends NewBookRequest<Book> {

	public NewBookJsonToXmlRequest() {
		super(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
				RequestUtils.URL_BOOK);
	}

	@Override
	protected Book createBook() {
		return new BookBuilder().withAuthor("Andrzej Json-Xml").withId(675L)
				.withTitle("Nowy tytul Xml to Json")
				.withImage(new byte[] { 12, 13, 14, 15 }).build();
	}
}
