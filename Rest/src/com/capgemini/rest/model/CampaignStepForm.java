package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CampaignStepForm {

	private long campaignId;
	
	private int stepOrder;
	
	@NotEmpty(message = "{camp.err.name}")
	private String name;
	
	@Size(max = 500, message = "camp.err.desc")
	private String description;
	
	@NotNull(message = "camp.err.startDate")
	private Date startDate;
	
	@NotNull(message = "camp.err.endDate")
	private Date endDate;
	
	private String hashTag;

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag.replaceAll("\\s+", "");
	}
	
	
}
