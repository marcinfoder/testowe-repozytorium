package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.capgemini.DateComparator;

public class MessageForm {

	private long campaignId;
	
	private long stepId;
	
	@DateTimeFormat(pattern = DateComparator.DATE_AND_TIME_FORMAT)
	@NotNull
	private Date publishDate;
	
	@Size(max = 140, message = "{mess.err.textSize}")
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
