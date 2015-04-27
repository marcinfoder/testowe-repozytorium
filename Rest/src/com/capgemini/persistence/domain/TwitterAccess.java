package com.capgemini.persistence.domain;

public class TwitterAccess {
	
	private long id;
	
	private String accessToken;
	
	private long userId;
	
	private boolean active;
	
	public TwitterAccess() {
		
	}
	
	public TwitterAccess(long id, long userId, String accessToken, boolean active) {
		this.id = id;
		this.userId = userId;
		this.accessToken = accessToken;
		this.active = active;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id: ").append(id)
		.append("userId: ").append(userId)
		.append("accessToken: ").append(accessToken)
		.append("active: ").append(active);
		return builder.toString();
	}
	
}
