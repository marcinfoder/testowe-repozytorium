package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonListHttp")
public class ListJsonHttpRequest extends ListHttpRequest {

	public ListJsonHttpRequest() {
		super(MediaType.APPLICATION_JSON);
	}

}
