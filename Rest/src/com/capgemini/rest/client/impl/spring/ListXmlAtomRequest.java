package com.capgemini.rest.client.impl.spring;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedOutput;

@Service("xmlAtomList")
public class ListXmlAtomRequest extends ListRequest {

	public ListXmlAtomRequest() {
		super(MediaType.APPLICATION_ATOM_XML);
	}

	@Override
	public void execute() {
		HttpEntity<String> entity = prepareEntity();
		ResponseEntity<Feed> response = restTemplate.exchange(
				requestUtils.getUrl(url), HttpMethod.GET, entity, Feed.class);
		System.out.println("Content Type: "
				+ response.getHeaders().getContentType());

		WireFeed atomFeed = response.getBody();
		WireFeedOutput output = new WireFeedOutput();
		try {
			System.out.println(output.outputString(atomFeed));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
