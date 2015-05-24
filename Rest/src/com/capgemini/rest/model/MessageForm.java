package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MessageForm {

	private long campaignId;
	
	private long stepId;
	
	private Date publishDate;
	
	@Size(max = 140, message = "{mess.text.size}")
	private String text;
	
	
	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public long getStepId() {
		return stepId;
	}

	public void setStepId(long stepId) {
		this.stepId = stepId;
	}


	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
