package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("htmlListHttp")
public class ListHtmlHttpRequest extends ListHttpRequest {

	public ListHtmlHttpRequest() {
		super(MediaType.TEXT_HTML, RequestUtils.URL_LIST_HTML);
	}

}
