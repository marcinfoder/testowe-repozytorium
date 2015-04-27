package com.capgemini.persistence.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
		super.saveOrUpdate(twitterAccess);
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
	public TwitterAccess getWithLogin(long login) {
		Query query = createQuery("from TwitterAccess Where GroupId= :userid");
		query.setLong("userid", login);
		return (TwitterAccess) query.list().get(0);
	}
	

	@Override
	public boolean add(long userId, String accessToken) {
		TwitterAccess tw = new TwitterAccess();
		tw.setActive(true);
		tw.setAccessToken(accessToken);
		tw.setGroupId(userId);
		super.saveOrUpdate(tw);
		return true;
	}
	
	


}
