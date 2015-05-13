package com.capgemini.persistence.domain;

import java.util.Date;

public class Message {

	private long messageId;
	private long groupId;
	private long userId;
	private long campaignId;
	private long stepId;
	private String twitterContent;
	private String facebookContent;
	private Date twitterPublishAt;
	private Date facebookPublishAt;
	private boolean twitterPublished;
	private boolean facebookPublished;
	private Date createdAt;
	
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("messageId").append(messageId).
		append("groupId").append(groupId).
		append("userId").append(userId).
		append("campaignId").append(campaignId).
		append("stepId").append(stepId).
		append("twitterContent").append(twitterContent).
		append("facebookContent").append(facebookContent).
		append("twitterPublishAt").append(twitterPublishAt).
		append("facebookPublishAt").append(facebookPublishAt).
		append("twitterPublished").append(twitterPublished).
		append("facebookPublished").append(facebookPublished).
		append("createdAt").append(createdAt);
		return sb.toString();
	}

	public long getMessageId() {
		return messageId;
	}


	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}


	public long getGroupId() {
		return groupId;
	}


	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


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


	public String getTwitterContent() {
		return twitterContent;
	}


	public void setTwitterContent(String twitterContent) {
		this.twitterContent = twitterContent;
	}


	public String getFacebookContent() {
		return facebookContent;
	}


	public void setFacebookContent(String facebookContent) {
		this.facebookContent = facebookContent;
	}


	public Date getTwitterPublishAt() {
		return twitterPublishAt;
	}

	public void setTwitterPublishAt(Date twitterPublishAt) {
		this.twitterPublishAt = twitterPublishAt;
	}

	public Date getFacebookPublishAt() {
		return facebookPublishAt;
	}

	public void setFacebookPublishAt(Date facebookPublishAt) {
		this.facebookPublishAt = facebookPublishAt;
	}

	public boolean isTwitterPublished() {
		return twitterPublished;
	}

	public void setTwitterPublished(boolean twitterPublished) {
		this.twitterPublished = twitterPublished;
	}

	public boolean isFacebookPublished() {
		return facebookPublished;
	}

	public void setFacebookPublished(boolean facebookPublished) {
		this.facebookPublished = facebookPublished;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
