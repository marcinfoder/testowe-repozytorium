package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.BookBuilder;
import com.capgemini.rest.client.RequestUtils;

@Service("newBookXml2Json")
public class NewBookXmlToJsonRequest extends NewBookRequest<Book> {

	public NewBookXmlToJsonRequest() {
		super(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				RequestUtils.URL_BOOK);
	}

	@Override
	protected Book createBook() {
		return new BookBuilder().withAuthor("Andrzej Xml-Json").withId(98L)
				.withTitle("Nowy tytul Xml to Json").build();
	}
}
