package com.capgemini.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;
import com.capgemini.service.IBookService;

@Service("bookService")
//@EnableTransactionManagement
public class BookService implements IBookService {

	@Resource(name = "hibernateBookDao")	
	private IBookDao bookDao;

	@Override
	@Transactional
	public Books findBooks(String author, String title) {
		return bookDao.getWithAuthorAndTitle(author, title);
	}

	@Override
	@Transactional
	public Books findAll() {
		return bookDao.list();
	}

	@Override
	@Transactional
	public Book findById(Long bookId) {
		return bookDao.getWith(bookId);
	}

}
