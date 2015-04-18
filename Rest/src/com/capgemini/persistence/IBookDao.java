package com.capgemini.persistence;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;

public interface IBookDao {

	public boolean add(Book book);

	public void update(Book book);

	public Book deleteWith(Long id);

	public Books list();

	public Book getWith(Long id);

	Books getWithAuthorAndTitle(String author, String title);
}
