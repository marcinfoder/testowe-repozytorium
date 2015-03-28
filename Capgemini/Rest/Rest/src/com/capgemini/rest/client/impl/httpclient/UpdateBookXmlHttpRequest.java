package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("updateXmlBookHttp")
public class UpdateBookXmlHttpRequest extends UpdateBookHttpRequest {

	public UpdateBookXmlHttpRequest() {
		super(null, MediaType.APPLICATION_XML, RequestUtils.URL_BOOK);
	}

	@Override
	protected String createBook() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><author>Andrew Zmieniony</author><id>1</id><image>AQID</image><title>Zaktualizowany tytul HTTP XML</title></book>";
	}

}
