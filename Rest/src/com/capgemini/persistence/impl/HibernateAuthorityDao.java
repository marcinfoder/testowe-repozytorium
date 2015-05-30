package com.capgemini.persistence.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.AuthorityDao;
import com.capgemini.persistence.domain.Authority;

@Repository("hibernateAuthorityDao")
public class HibernateAuthorityDao extends AbstractDao<Authority> implements AuthorityDao {

	
	@Autowired
	public HibernateAuthorityDao(SessionFactory sessionFactory){
		super(sessionFactory, Authority.class);
	}

	@Override
	public boolean createAuthority(Authority auth) {
		super.save(auth);
		return true;
	}

	@Override
	public void updateAuthority(Authority auth) {
		super.update(auth);
	}

	@Override
	public Authority deleteAuthority(long authId) {
		Authority auth = getById(authId);
		if(auth != null)
		{
			super.delete(auth);
		}
		return auth;
	}

	@Override
	public List<Authority> getAuthoritiesListByUserId(long userId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("UserId", userId));
		return crit.list();
	}
}
