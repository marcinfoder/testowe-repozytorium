package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("newBookString2Xml")
public class NewBookStringToXmRequest extends NewBookRequest<String> {

	public NewBookStringToXmRequest() {
		super(MediaType.APPLICATION_XML, MediaType.TEXT_XML, RequestUtils.URL_BOOK_STRING);
	}

	@Override
	protected String createBook() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book id=\"888\"><author>Andrew K</author><title>Nowy tytul String 2 XML</title></book>";
	}
}
