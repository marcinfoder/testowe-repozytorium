package com.capgemini.rest.client.impl.spring;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.rest.client.RequestUtils;

@Service("getImage")
public class GetImageRequest extends AbstractRequest {

	public GetImageRequest() {
		super(MediaType.IMAGE_JPEG, RequestUtils.URL_IMAGE);
	}

	@Override
	public void execute() throws Exception {
		HttpEntity<String> entity = prepareEntity();
		ResponseEntity<BufferedImage> response = restTemplate.exchange(
				requestUtils.getUrl(url), HttpMethod.GET, entity,
				BufferedImage.class);
		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());
		BufferedImage file = response.getBody();
		File outputfile = new File(getFilePath());
		System.out.println("new file created: " + outputfile.getAbsolutePath());
		ImageIO.write(file, "jpg", outputfile);
	}

	private String getFilePath() {
		return "download/" + String.valueOf(System.currentTimeMillis())
				+ "image.jpg";
	}

}
