package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("xmlRemoveBook")
public class DeleteBookXmlRequest extends DeleteBookRequest {

	public DeleteBookXmlRequest() {
		super (MediaType.APPLICATION_XML, 2L);
	}

}
