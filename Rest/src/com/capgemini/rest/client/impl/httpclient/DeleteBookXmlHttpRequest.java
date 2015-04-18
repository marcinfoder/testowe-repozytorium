package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlRemoveBookHttp")
public class DeleteBookXmlHttpRequest extends DeleteBookHttpRequest {

	public DeleteBookXmlHttpRequest() {
		super(MediaType.APPLICATION_XML, 5L);
	}

}
