package com.capgemini.persistence.domain;

public class User {

	private long id;
	private String login;
	private String password;
	private boolean active;
	private long groupId;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("userid:").append(id).
		append("groupid:").append(groupId).
		append("login:").append(login).
		append("active:").append(active);
		return sb.toString();
	}
	
	public String getLogin() {
		return login;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
