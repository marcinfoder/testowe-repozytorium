package com.capgemini.persistence.domain;

import java.util.Date;

public class TwitterAccess {
	
	private long id;
	private String accessToken;
	private String accessTokenSecret;
	private long groupId;
	private boolean active;
	private Date createdAt;
	private Date updatedAt;
	
	public TwitterAccess() {
		
	}
	
	public TwitterAccess(long id, long userId, String accessToken, boolean active) {
		this.id = id;
		this.groupId = userId;
		this.accessToken = accessToken;
		this.active = active;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("taid: ").append(id)
		.append("groupId: ").append(groupId)
		.append("accessToken: ").append(accessToken)
		.append("active: ").append(active)
		.append("createdAt").append(createdAt.toString())
		.append("updatedAt").append(updatedAt.toString());
		return builder.toString();
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
