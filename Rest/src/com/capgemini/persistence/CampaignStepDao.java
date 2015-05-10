package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.CampaignStep;

public interface CampaignStepDao {

	public boolean add(CampaignStep step);
	
	public void update(CampaignStep step);

	public CampaignStep getWith(long id);
	
	public CampaignStep deleteWith(Long id);
	
	public List getListByCampaignId(long campaignId);
	
}
