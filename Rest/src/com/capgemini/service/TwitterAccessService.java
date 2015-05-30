package com.capgemini.service;

import com.capgemini.persistence.domain.TwitterAccess;

public interface TwitterAccessService {

	public TwitterAccess findByLogin(String login);
	
	public void addTwitterAccess(String accessToken, String accessTokenSecret, long twitterUserId);
	
	public void updateTwitterAccess(long twitterAccessId, String accessToken, String accessTokenSecret);
	
	public void deactivateTwitterAccess(long twitterAccessId);
	
}
