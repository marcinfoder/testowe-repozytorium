package com.capgemini.service;

import java.util.List;

import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.CampaignStep;

public interface CampaignService {

	public List<?> getCampaignsByGroupId(long groupId);
	
	public List<?> getStepsByGroupId(long groupId);
	
	public Campaign getCampaignById(long id);
	
	public CampaignStep getStepById(long id);
	
	public List<?> getStepsByCampaignId(long campaignId);
	
	public void deleteStepById(long id);
	
	public void deleteCampaignById(long id);
	
	public boolean addStep(CampaignStep campStep);
	
	public boolean addCampaign(Campaign camp);
	
	public List<?> getCampaignByUserLogin(String login);
	
}
