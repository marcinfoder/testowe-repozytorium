package com.capgemini.rest.client.impl.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service("jsonList")
public class ListJsonRequest extends ListRequest {

	public ListJsonRequest() {
		super(MediaType.APPLICATION_JSON);
	}
}
