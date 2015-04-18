package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonBookHttp")
public class GetBookJsonHttpRequest extends GetBookHttpRequest {

	public GetBookJsonHttpRequest() {
		super(MediaType.APPLICATION_JSON);
	}

}
