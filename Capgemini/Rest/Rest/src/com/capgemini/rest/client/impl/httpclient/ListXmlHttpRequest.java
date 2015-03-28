package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlAtomListHttp")
public class ListXmlHttpRequest extends ListHttpRequest {

	public ListXmlHttpRequest() {
		super(MediaType.APPLICATION_ATOM_XML);
	}
}
