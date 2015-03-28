package com.capgemini.rest.client.impl.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.capgemini.rest.client.IRequest;
import com.capgemini.rest.client.RequestUtils;

public abstract class HttpRequest implements IRequest {

	protected final String url;
	private final MediaType acceptedMediaType;

	@Autowired
	protected RequestUtils requestUtils;

	public HttpRequest(MediaType acceptedMediaType) {
		this.acceptedMediaType = acceptedMediaType;
		this.url = RequestUtils.URL_LIST;
	}

	public HttpRequest(MediaType acceptedMediaType, String url) {
		this.acceptedMediaType = acceptedMediaType;
		this.url = url;
	}

	@Override
	public void execute() throws Exception {
		execute(createRequest());
	}

	protected abstract HttpRequestBase createRequest();

	public void execute(HttpRequestBase request) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		if (acceptedMediaType != null) {
			request.addHeader("Accept", acceptedMediaType.toString());
		}
		HttpResponse response = httpclient.execute(request);
		HttpEntity entity = response.getEntity();
		printEntity(entity);
		request.releaseConnection();
	}

	private void printEntity(HttpEntity entity) throws IOException {
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(instream));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} finally {
				instream.close();
			}
		}
	}

}
