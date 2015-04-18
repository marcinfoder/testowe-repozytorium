package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("newBookJson2XmlHttp")
public class NewBookJsonToXmlHttpRequest extends NewBookHttpRequest {

	public NewBookJsonToXmlHttpRequest() {
		super(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
				RequestUtils.URL_BOOK);
	}

	@Override
	protected String createBook() {
		return "{\"id\":131,\"jsonTitle\":\"Nowy tytul HTTP Xml To Json\",\"author\":\"Andrew K\",\"image\":\"AQID\"}";
	}

}
