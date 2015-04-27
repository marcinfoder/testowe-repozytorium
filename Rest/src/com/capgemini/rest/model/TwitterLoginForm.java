package com.capgemini.rest.model;

import javax.validation.constraints.Pattern;

public class TwitterLoginForm {
	
	@Pattern(regexp = "\\d+")
	private String twitterPin;

	public String getTwitterPin() {
		return twitterPin;
	}

	public void setTwitterPin(String twitterPin) {
		this.twitterPin = twitterPin;
	}
}
