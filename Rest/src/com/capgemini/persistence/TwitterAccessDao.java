package com.capgemini.persistence;

import com.capgemini.persistence.domain.TwitterAccess;

public interface TwitterAccessDao {

	public boolean add(long userId, String accessToken);
	
	public boolean add(TwitterAccess twitterAccess);

	public void update(TwitterAccess twitterAccess);

	public TwitterAccess deleteWith(long id);

	public TwitterAccess getWith(long id);

	public TwitterAccess getWithLogin(long login);
}
