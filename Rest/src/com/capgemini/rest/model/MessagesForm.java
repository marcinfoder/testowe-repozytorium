package com.capgemini.rest.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class MessagesForm {

	@NotEmpty
	@Size(max = 5)
	private String Tweet;

	public String getTweet() {
		return Tweet;
	}

	public void setTweet(String title) {
		this.Tweet = title;
	}
}
