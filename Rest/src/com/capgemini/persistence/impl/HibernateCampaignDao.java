package com.capgemini.persistence.impl;

import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.CaptureBinding;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
			campaign.setActive(false);
			super.update(campaign);
		}
		return campaign;
	}

	@Override
	public Campaign getWith(long id) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("campaignId", id));
		crit.add(Restrictions.eq("active", true));
		return (Campaign) crit.uniqueResult();
	}

	@Override
	public List<?> getListByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId));
		crit.add(Restrictions.eq("active", true));
		return crit.list();
	}

	@Override
	public List<?> getListByName(String name) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.like("name", name));
		crit.add(Restrictions.like("active", true));
		return crit.list();
	}

}
