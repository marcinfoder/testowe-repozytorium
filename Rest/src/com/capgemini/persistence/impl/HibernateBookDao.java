package com.capgemini.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;

@Repository("hibernateBookDao")
public class HibernateBookDao extends AbstractDao<Book> implements IBookDao {

	@Autowired
	public HibernateBookDao(SessionFactory sessionFactory) {
		super(sessionFactory, Book.class);
	}

	@Override
	public boolean add(Book book) {
		super.save(book);
		return true;
	}

	@Override
	public Book deleteWith(Long id) {
		Book book = getById(id);
		if (book != null) {
			delete(book);
		}
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Books list() {
		Query query = createQuery("from Book b where length(title) > 10");
		return new Books(query.list(), "new owner");
	}

	@Override
	public Book getWith(Long id) {
		return getById(id);
	}
	
	@Override
	@PreAuthorize("#author != 'm' or hasRole('guest')")
	public Books getWithAuthorAndTitle(String author, String title) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.like(Book.PROP_AUTHOR, author, MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		List<Book> list = getBooks(criteria);
		return new Books(list, "new owner");
	}

	@SuppressWarnings("unchecked")
	@PostFilter(value = "(filterObject.author == 'Zygmunt Kowalski')")
	private List<Book> getBooks(Criteria criteria) {
		return criteria.list();
	}

}
