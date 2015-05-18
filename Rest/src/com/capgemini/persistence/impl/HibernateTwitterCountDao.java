package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.TwitterCountDao;
import com.capgemini.persistence.domain.TwitterCount;

@Repository("hibernateTwitterCountDao")
public class HibernateTwitterCountDao extends AbstractDao<TwitterCount> implements TwitterCountDao {

	@Autowired
	public HibernateTwitterCountDao(SessionFactory sessionFactory) {
		super(sessionFactory, TwitterCount.class);
	}

	@Override
	public boolean add(TwitterCount tc) {
		super.save(tc);
		return true;
	}

	@Override
	public TwitterCount deleteWith(long id) {
		TwitterCount tc = getWith(id);
		if(tc != null) {
			super.delete(tc);
		}
		return tc;
	}

	@Override
	public TwitterCount getWith(long id) {
		return getById(id);
	}

	@Override
	public List getListByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}
	
}
