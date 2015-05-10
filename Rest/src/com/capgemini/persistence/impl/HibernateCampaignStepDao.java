package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.CampaignStepDao;
import com.capgemini.persistence.domain.CampaignStep;

@Repository("hibernateCampaignStepDao")
public class HibernateCampaignStepDao extends AbstractDao<CampaignStep> implements CampaignStepDao {

	@Autowired
	public HibernateCampaignStepDao(SessionFactory sessionFactory) {
		super(sessionFactory, CampaignStep.class);
	}

	@Override
	public boolean add(CampaignStep step) {
		super.save(step);
		return true;
	}

	@Override
	public CampaignStep getWith(long id) {
		return getById(id);
	}

	@Override
	public CampaignStep deleteWith(Long id) {
		CampaignStep cs = getById(id);
		if(cs != null) {
			super.delete(cs);
		}
		return cs;
	}

	@Override
	public List<?> getListByCampaignId(long campaignId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("campaignId", campaignId));
		return crit.list();
	}

}
