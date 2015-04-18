package com.capgemini.rest.client.impl.httpclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("updateJsonBookHttp")
public class UpdateBookJsonHttpRequest extends UpdateBookHttpRequest {

	public UpdateBookJsonHttpRequest() {
		super(null, MediaType.APPLICATION_JSON, RequestUtils.URL_BOOK);
	}

	@Override
	protected String createBook() {
		return "{\"id\":1,\"jsonTitle\":\"Zaktualizoway tytul HTTP Json\",\"author\":\"Andrew K-Zmieniony\",\"image\":\"AQID\"}";
	}

}
