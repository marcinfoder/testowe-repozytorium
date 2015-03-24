package com.getsocial.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.getsocial.app.model.User;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> list = sessionFactory.getCurrentSession()
			.createCriteria(User.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}

}
