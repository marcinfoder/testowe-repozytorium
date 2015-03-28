package com.capgemini.service;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;

public interface IBookService {

	Books findBooks(String author, String title);

	Books findAll();

	Book findById(Long bookId);
}
