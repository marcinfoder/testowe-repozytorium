package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlListHttp")
public class ListXmlAtomHttpRequest extends ListHttpRequest {

	public ListXmlAtomHttpRequest() {
		super(MediaType.APPLICATION_XML);
	}
}
