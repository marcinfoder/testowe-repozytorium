package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlList")
public class ListXmlRequest extends ListRequest {

	public ListXmlRequest() {
		super(MediaType.APPLICATION_XML);
	}
}
