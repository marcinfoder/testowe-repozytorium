package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.PhotoDao;
import com.capgemini.persistence.domain.Photo;

@Repository("hibernatePhotoDao")
public class HibernatePhotoDao extends AbstractDao<Photo> implements PhotoDao {

	@Autowired
	public HibernatePhotoDao(SessionFactory sessionFactory) {
		super(sessionFactory, Photo.class);
	}

	@Override
	public boolean add(Photo photo) {
		super.save(photo);
		return true;
	}

	@Override
	public Photo deleteWith(long id) {
		Photo photo = getById(id);
		if(photo != null) {
			super.delete(photo);
		}
		return photo;
	}

	@Override
	public Photo getWith(long id) {
		return super.getById(id);
	}

	@Override
	public List getListByGroupId(long groupId) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.eq("groupId", groupId)); //mozliwe ze potrzebne bedzie isActive
		return crit.list();
	}

}
