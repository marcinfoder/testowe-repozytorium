package com.capgemini.service;

import com.capgemini.persistence.domain.TwitterAccess;

public interface TwitterAccessService {

	public TwitterAccess findByLogin(String login);
	
	public void addTwitterAccess(String accessToken);
}
