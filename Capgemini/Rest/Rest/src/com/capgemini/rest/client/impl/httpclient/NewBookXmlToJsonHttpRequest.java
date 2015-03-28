package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("newBookXml2JsonHttp")
public class NewBookXmlToJsonHttpRequest extends NewBookHttpRequest {

	public NewBookXmlToJsonHttpRequest() {
		super(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				RequestUtils.URL_BOOK);
	}

	@Override
	protected String createBook() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><author>Andrew K</author><id>11</id><image>AQID</image><title>Nowy tytul HTTP XML to JSON</title></book>";
	}
}
