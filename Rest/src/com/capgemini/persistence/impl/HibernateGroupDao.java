package com.capgemini.persistence.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.GroupDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.Group;
import com.capgemini.persistence.domain.User;

@Repository("hibernateGroupDao")
public class HibernateGroupDao extends AbstractDao<Group> implements GroupDao {

	@Autowired
	public HibernateGroupDao(SessionFactory sessionFactory) {
		super(sessionFactory, Group.class);
	}

	@Override
	public long createGroup(String name, String description) {
		Group g = new Group();
		g.setDescription(description);
		g.setName(name);
		g.setCreatedAt(new Date());
		
		super.save(g);
		return g.getGroupId();
	}

}
