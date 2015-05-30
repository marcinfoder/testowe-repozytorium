package com.capgemini.service;

import java.util.List;

import com.capgemini.persistence.domain.Authority;

public interface AuthorityService {
	public void createAuthority(long userId, String role);
	
	public void deleteAuthority(long authId);
	
	public List<Authority> getAuthListByUserLogin(String login); 
	
	public List<Authority> getAuthListByUserId(long userId);
}
