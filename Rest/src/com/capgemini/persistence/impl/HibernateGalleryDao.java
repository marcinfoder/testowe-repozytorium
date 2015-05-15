package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.GalleryDao;
import com.capgemini.persistence.domain.Gallery;

@Repository("hibernateGalleryDao")
public class HibernateGalleryDao extends AbstractDao<Gallery> implements GalleryDao{

	@Autowired
	public HibernateGalleryDao(SessionFactory sessionFactory) {
		super(sessionFactory, Gallery.class);
	}

	@Override
	public boolean add(Gallery gallery) {
		super.save(gallery);
		return true;
	}

	@Override
	public Gallery deleteWith(long id) {
		Gallery g = getById(id);
		if(g != null) {
			super.delete(g);
		}
		return g;
	}

	@Override
	public Gallery getWith(long id) {
		return getById(id);
	}

	@Override
	public List getListByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId));
		return crit.list();
	}

	@Override
	public List getListByCampaign(long campaignId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("campaignId", campaignId));
		return crit.list();
	}

}
