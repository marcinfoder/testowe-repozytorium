package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("htmlList")
public class ListHtmlRequest extends ListRequest {

	public ListHtmlRequest() {
		super(MediaType.TEXT_HTML, RequestUtils.URL_LIST_HTML);
	}

	public void execute() {
		HttpEntity<String> entity = prepareEntity();
		ResponseEntity<String> response = restTemplate.exchange(requestUtils.getUrl(url),
				HttpMethod.GET, entity, String.class);

		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		String books = response.getBody();
		System.out.println(books);
	}
}
