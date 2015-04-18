package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonRemoveBookHttp")
public class DeleteBookJsonHttpRequest extends DeleteBookHttpRequest {

	public DeleteBookJsonHttpRequest() {
		super(MediaType.APPLICATION_JSON, 4L);
	}

}
