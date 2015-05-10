package com.capgemini.persistence.domain;

import java.util.Date;

public class CampaignStep {
	private long stepId;
	private long campaignId;
	private int stepOrder;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("stepId").append(stepId).
		append("campaignId").append(campaignId).
		append("stepOrder").append(stepOrder).
		append("name").append(name).
		append("description").append(description).
		append("startDate").append(startDate).
		append("endDate").append(endDate);
		return sb.toString();
	}

	public long getStepId() {
		return stepId;
	}

	public void setStepId(long stepId) {
		this.stepId = stepId;
	}

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
	
	
}
