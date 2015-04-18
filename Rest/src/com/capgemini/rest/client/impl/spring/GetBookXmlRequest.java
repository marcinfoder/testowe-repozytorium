package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlBook")
public class GetBookXmlRequest extends GetBookRequest {

	public GetBookXmlRequest() {
		super (MediaType.APPLICATION_XML);
	}

}
