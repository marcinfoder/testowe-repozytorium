package com.capgemini.persistence.domain;

import java.util.Date;

public class Campaign {
	private long campaignId;
	private long groupId;
	private boolean twitterConnection;
	private boolean facebookConnection;
	private String name;
	private String description;
	private int numberOfSteps;
	private Date createdAt;
	private Date startDate;
	private Date endDate;
	private boolean active;
	private String hashTag;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("campaignId").append(campaignId).
		append("groupId").append(groupId).
		append("twitterConnection").append(twitterConnection).
		append("facebookConnection").append(facebookConnection).
		append("name").append(name).
		append("description").append(description).
		append("numberOfSteps").append(numberOfSteps).
		append("createdAt").append(createdAt).
		append("startDate").append(startDate).
		append("endDate").append(endDate);
		return sb.toString();
	}

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public boolean isTwitterConnection() {
		return twitterConnection;
	}

	public void setTwitterConnection(boolean twitterConnection) {
		this.twitterConnection = twitterConnection;
	}

	public boolean isFacebookConnection() {
		return facebookConnection;
	}

	public void setFacebookConnection(boolean facebookConnection) {
		this.facebookConnection = facebookConnection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
	public boolean isExpired()
	{
		if(this.getEndDate().after(new Date()))
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
}
