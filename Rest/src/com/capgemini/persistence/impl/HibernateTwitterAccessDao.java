package com.capgemini.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.TwitterAccessDao;
import com.capgemini.persistence.domain.TwitterAccess;

@Repository("hibernateTwitterAccessDao")
public class HibernateTwitterAccessDao extends AbstractDao<TwitterAccess> implements TwitterAccessDao {

	@Autowired
	public HibernateTwitterAccessDao(SessionFactory sessionFactory) {
		super(sessionFactory, TwitterAccess.class);
	}

	@Override
	public boolean add(TwitterAccess twitterAccess) {
		super.save(twitterAccess);
		return true;
	}

	@Override
	public void update(TwitterAccess twitterAccess) {
		super.update(twitterAccess);
	}

	@Override
	public TwitterAccess deleteWith(long id) {
		TwitterAccess ta = getById(id);
		if(ta != null) {
			super.delete(ta);
		}
		return ta;
	}

	@Override
	public TwitterAccess getWith(long id) {
		return getById(id);
	}

	@Override
	public TwitterAccess getWithGroup(long id) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", id));
		crit.add(Restrictions.eq("active", true));
		return (TwitterAccess) crit.uniqueResult();
	}
}
