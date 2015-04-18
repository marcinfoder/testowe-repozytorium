package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlBookHttp")
public class GetBookXmlHttpRequest extends GetBookHttpRequest {

	public GetBookXmlHttpRequest() {
		super(MediaType.APPLICATION_XML);
	}

}
