package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.CampaignDao;
import com.capgemini.persistence.CampaignStepDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.CampaignService;

@Service("CampignService")
public class CampaignServiceImpl implements CampaignService {

	@Resource(name = "hibernateCampaignDao")
	private CampaignDao campaign;
	
	@Resource(name = "hibernateCampaignStepDao")
	private CampaignStepDao campaignStep;
	
	@Resource(name = "hibernateUserDao")
	private UserDao userDao;
	
	@Override
	@Transactional
	public List<?> getCampaignsByGroupId(long groupId) {
		return campaign.getListByGroupId(groupId);
	}

	@Override
	@Transactional
	public List<?> getStepsByGroupId(long groupId) {
		List<?> camps = campaign.getListByGroupId(groupId);
		List<CampaignStep> result = new ArrayList<CampaignStep>();
		for(Object item : camps) {
			Campaign camp = (Campaign)item;
			List<?> steps = campaignStep.getListByCampaignId(camp.getCampaignId());
			for(Object step : steps) {
				result.add((CampaignStep)step);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public Campaign getCampaignById(long id) {
		return campaign.getWith(id);
	}

	@Override
	@Transactional
	public CampaignStep getStepById(long id) {
		return campaignStep.getWith(id);
	}

	@Override
	@Transactional
	public List<?> getStepsByCampaignId(long campaignId) {
		return campaignStep.getListByCampaignId(campaignId);
	}

	@Override
	@Transactional
	public void deleteStepById(long id) {
		campaignStep.deleteWith(id);
	}

	@Override
	@Transactional
	public void deleteCampaignById(long id) {
		campaign.deleteWith(id);
	}

	@Override
	@Transactional
	public boolean addStep(CampaignStep campStep) {
		campaignStep.add(campStep);
		return true;
	}

	@Override
	@Transactional
	public boolean addCampaign(Campaign camp) {
		camp.setActive(true);
		campaign.add(camp);
		return true;
	}

	@Override
	@Transactional
	public List<?> getCampaignByUserLogin(String login) {
		return getCampaignsByGroupId(userDao.findByName(login).getGroupId());
	}

}
