package com.capgemini.persistence.domain;

import com.capgemini.UserRoles;

public class Authority {
	private long AuthorityId;
	private long UserId;
	private String Role;
	
	public long getAuthorityId() {
		return AuthorityId;
	}
	public void setAuthorityId(long authorityId) {
		AuthorityId = authorityId;
	}
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
	public void Admin()
	{
		Role = UserRoles.ADMIN;
	}
	
	
}
