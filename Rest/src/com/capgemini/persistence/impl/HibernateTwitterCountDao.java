package com.capgemini.persistence.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		Criteria crit = createCriteriaWithEntityName("TwitterCountsAll");
		crit.addOrder(Order.asc("date"));
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}

	@Override
	public List getDailyListByGroupId(long groupId) {
		Criteria crit = createCriteriaWithEntityName("TwitterCountsDaily");
		crit.addOrder(Order.asc("date"));
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}

	@Override
	public List getDailyListByGroupId(long groupId, Date from, Date to) {
		Criteria crit = createCriteriaWithEntityName("TwitterCountsDaily");
		crit.add(Restrictions.eq("groupId", groupId));
		crit.addOrder(Order.asc("date"));
		if(from != null) {
			crit.add(Restrictions.ge("date", from));
		}
		if(to != null){
			crit.add(Restrictions.le("date", to));
		}
		return crit.list();
	}

	@Override
	public List getHourlyListByGroupId(long groupId) {
		Criteria crit = createCriteriaWithEntityName("TwitterCountsHourly");
		crit.add(Restrictions.eq("groupId", groupId));
		crit.addOrder(Order.asc("date"));
		return crit.list();
	}

	@Override
	public List getHourlyListByGroupId(long groupId, Date from, Date to) {
		Criteria crit = createCriteriaWithEntityName("TwitterCountsHourly");
		crit.add(Restrictions.eq("groupId", groupId));
		crit.addOrder(Order.asc("date"));
		if(from != null) {
			crit.add(Restrictions.ge("date", from));
		}
		if(to != null){
			crit.add(Restrictions.le("date", to));
		}
		return crit.list();
	}
	
}
