package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.CampaignDao;
import com.capgemini.persistence.domain.Campaign;

@Repository("hibernateCampaignDao")
public class HibernateCampaignDao extends AbstractDao<Campaign> implements CampaignDao {
//public class HibernateCampaignDao extends AbstractDao<Campaign> implements CampaignDao {

	@Autowired
	public HibernateCampaignDao(SessionFactory session) {
		super(session, Campaign.class);
	}

	@Override
	public boolean add(Campaign campaign) {
		super.save(campaign);
		return true;
	}

	@Override
	public void update(Campaign campaign) {
		super.update(campaign);
	}

	@Override
	public Campaign deleteWith(long id) {
		Campaign campaign = getById(id);
		if(campaign != null) {
			super.delete(campaign);
		}
		return campaign;
	}

	@Override
	public Campaign getWith(long id) {
		return getById(id);
	}

	@Override
	public List<?> getListByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}

	@Override
	public List<?> getListByName(String name) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.like("name", name));
		return crit.list();
	}

}
