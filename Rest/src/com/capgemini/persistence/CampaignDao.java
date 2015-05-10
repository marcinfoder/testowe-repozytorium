package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.Campaign;

public interface CampaignDao {

	public boolean add(Campaign campaign);

	public void update(Campaign campaign);
	
	public Campaign deleteWith(long id);
	
	public Campaign getWith(long id);
	
	public List getListByGroupId(long groupId);
	
	public List getListByName(String name);
}
