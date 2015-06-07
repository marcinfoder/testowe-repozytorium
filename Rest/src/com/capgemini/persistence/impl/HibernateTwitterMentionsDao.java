package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.TwitterMentionsDao;
import com.capgemini.persistence.domain.TwitterMentions;

@Repository("TwitterMentionsDao")
public class HibernateTwitterMentionsDao extends AbstractDao<TwitterMentions> implements TwitterMentionsDao{

	@Autowired
	public HibernateTwitterMentionsDao(SessionFactory sessionFactory) {
		super(sessionFactory, TwitterMentions.class);
	}

	@Override
	public List<TwitterMentions> getMentionsByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}
	

}
