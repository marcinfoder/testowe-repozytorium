package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonRemoveBook")
public class DeleteBookJsonRequest extends DeleteBookRequest {

	public DeleteBookJsonRequest() {
		super (MediaType.APPLICATION_JSON, 3L);
	}

}
