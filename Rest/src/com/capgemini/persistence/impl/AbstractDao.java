package com.capgemini.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public abstract class AbstractDao<T> {

	private SessionFactory sessionFactory;
	private Class<T> claz;

	public AbstractDao(SessionFactory sessionFactory, Class<T> claz) {
		this.sessionFactory = sessionFactory;
		this.claz = claz;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) getCurrentSession().get(claz, id);
	}

	protected Criteria createCriteria() {
		return getCurrentSession().createCriteria(claz);
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Query createQuery(String queryString) {
		return getCurrentSession().createQuery(queryString);
	}

	public void save(T object) {
		getCurrentSession().save(object);
	}

	public void saveOrUpdate(T object) {
		getCurrentSession().saveOrUpdate(object);
	}

	public void update(T object) {
		getCurrentSession().update(object);
	}

	public void delete(T object) {
		getCurrentSession().delete(object);
	}
}
