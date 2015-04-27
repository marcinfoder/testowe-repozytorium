package com.capgemini.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.IUserDao;
import com.capgemini.persistence.domain.User;

@Repository
public class HibernateUserDao extends AbstractDao<User> implements IUserDao {

	@Autowired
	public HibernateUserDao(SessionFactory sessionFactory) {
		super(sessionFactory, User.class);
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByName(String name) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("login", name));
		return (User) criteria.uniqueResult();
	}

}
