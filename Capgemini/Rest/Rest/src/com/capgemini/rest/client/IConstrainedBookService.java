package com.capgemini.rest.client;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;

import com.capgemini.persistence.domain.Book;

public interface IConstrainedBookService {

	@PostFilter(value = "filterObject.author != 'Zygmunt Kowalski'")
	List<Book> findBooks(String author, String title);
}
