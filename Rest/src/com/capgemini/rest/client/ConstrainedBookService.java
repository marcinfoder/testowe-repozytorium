package com.capgemini.rest.client;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;

@Service
public class ConstrainedBookService implements IConstrainedBookService {

	@Resource(name = "hibernateBookDao")
	private IBookDao bookDao;

	@Override
	@Transactional
	public List<Book> findBooks(String author, String title) {
		return bookDao.getWithAuthorAndTitle(author, title).getBooks();
	}

}
