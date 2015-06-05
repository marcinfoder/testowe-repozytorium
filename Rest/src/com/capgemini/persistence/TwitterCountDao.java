package com.capgemini.persistence;

import java.util.Date;
import java.util.List;

import com.capgemini.persistence.domain.TwitterCount;

public interface TwitterCountDao {

	public boolean add(TwitterCount tc);
	
	public void update(TwitterCount tc);
	
	public TwitterCount deleteWith(long id);
	
	public TwitterCount getWith(long id);
	
	public List getListByGroupId(long groupId);
	
	public List getDailyListByGroupId(long groupId);
	
	public List getDailyListByGroupId(long groupId, Date from, Date to);
	
	public List getHourlyListByGroupId(long groupId);
	
	public List getHourlyListByGroupId(long groupId, Date from, Date to);
	
}
