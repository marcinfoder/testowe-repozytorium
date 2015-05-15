package com.capgemini.persistence.domain;

import java.util.Date;

public class Gallery {
	private long galleryId;
	private long groupId;
	private long campaignId;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("galleryId").append(galleryId).
		append("groupId").append(groupId).
		append("campaignId").append(campaignId).
		append("name").append(name).
		append("createdAt").append(createdAt).
		append("updatedAt").append(updatedAt);
		return sb.toString();
	}

	public long getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(long galleryId) {
		this.galleryId = galleryId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
