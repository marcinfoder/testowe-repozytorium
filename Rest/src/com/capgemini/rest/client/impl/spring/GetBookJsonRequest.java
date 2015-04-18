package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonBook")
public class GetBookJsonRequest extends GetBookRequest {

	public GetBookJsonRequest() {
		super (MediaType.APPLICATION_JSON);
	}

}
