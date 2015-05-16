package com.capgemini.persistence.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.MessageDao;
import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.Message;

@Repository("hibernateMessageDao")
public class HibernateMessageDao extends AbstractDao<Message> implements MessageDao{

	@Autowired
	public HibernateMessageDao(SessionFactory sessionFactory){
		super(sessionFactory, Message.class);
	}

	@Override
	public List<?> getListByCampaignId(long campaignId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("campaignId", campaignId));
		return crit.list();
	}

	@Override
	public List<?> getListByStepId(long stepId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("stepId", stepId));
		return crit.list();
	}

	@Override
	public Message getWith(long messageId) {
		return getById(messageId);
	}

	@Override
	public boolean add(Message message) {
		super.save(message);
		return true;
	}

	@Override
	public List<?> getListByCampaignIdByStepId(long campaignId, long stepId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("campaignId", campaignId));
		crit.add(Restrictions.eq("stepId", stepId));
		return crit.list();
	}

	@Override
	public Message deleteWith(Long messageId) {
		Message message = getById(messageId);
		if(message != null) {
			super.delete(message);
		}
		return message;
	}



}
