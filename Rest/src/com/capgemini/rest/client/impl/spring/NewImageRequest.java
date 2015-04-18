package com.capgemini.rest.client.impl.spring;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.capgemini.rest.client.RequestUtils;

@Service("addImage")
public class NewImageRequest extends AbstractRequest {

	public NewImageRequest() {
		super(MediaType.TEXT_PLAIN, RequestUtils.URL_IMAGE);
	}

	@Override
	public void execute() throws Exception {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("field", "value");
		parts.add("file", new FileSystemResource("pictures/champions.jpg"));
		HttpEntity<MultiValueMap<String, Object>> entity = prepareEntity(parts,
				MediaType.MULTIPART_FORM_DATA);
		ResponseEntity<String> response = restTemplate
				.exchange(requestUtils.getUrl(url), HttpMethod.POST, entity,
						String.class);

		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		String result = response.getBody();
		System.out.println(result);
	}

}
