package com.capgemini.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capgemini.persistence.BookDataSource;
import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;

@Repository("bookDao")
public class BookDao implements IBookDao {

	private BookDataSource dataSource = BookDataSource.instance();

	@Override
	public boolean add(Book book) {
		Map<Long, Book> books = dataSource.getBooks();
		if (books.containsKey(book.getId())) {
			return false;
		} else {
			books.put(book.getId(), book);
			return true;
		}
	}

	@Override
	public void update(Book book) {
		Map<Long, Book> books = dataSource.getBooks();
		if (books.containsKey(book.getId())) {
			books.put(book.getId(), book);
		}
	}

	@Override
	public Book deleteWith(Long id) {
		Map<Long, Book> books = dataSource.getBooks();
		return books.remove(id);
	}

	@Override
	public Books list() {
		Map<Long, Book> books = dataSource.getBooks();
		return new Books(new ArrayList<Book>(books.values()),
				dataSource.getOwner());
	}

	@Override
	public Book getWith(Long id) {
		Map<Long, Book> books = dataSource.getBooks();
		return books.get(id);
	}

	@Override
	public Books getWithAuthorAndTitle(String author, String title) {
		Map<Long, Book> books = dataSource.getBooks();
		List<Book> result = new ArrayList<Book>();
		for (Book book : books.values()) {
			if (book.getAuthor().toLowerCase().contains(author.toLowerCase())
					&& book.getTitle().toLowerCase()
							.contains(title.toLowerCase())) {
				result.add(book);
			}
		}
		return new Books(result, dataSource.getOwner());
	}
}
