package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("newBookString2XmlHttp")
public class NewBookStringToXmlHttpRequest extends NewBookHttpRequest {

	public NewBookStringToXmlHttpRequest() {
		super(MediaType.APPLICATION_XML, MediaType.TEXT_XML,
				RequestUtils.URL_BOOK_STRING);
	}

	@Override
	protected String createBook() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><author>Lucy String</author><id>999</id><image>AQID</image><title>Nowy tytul HTTP String to JSON</title></book>";
	}

}
