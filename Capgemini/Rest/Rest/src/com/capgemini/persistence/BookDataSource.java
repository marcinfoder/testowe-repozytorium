package com.capgemini.persistence;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.BookBuilder;

public class BookDataSource {

	private Map<Long, Book> books;

	private final String owner = "Antek K.";

	private static BookDataSource instance;

	public static BookDataSource instance() {
		if (instance == null) {
			instance = new BookDataSource();
			initializeBooks();
		}
		return instance;
	}

	private BookDataSource() {

	}

	private static void initializeBooks() {
		instance.books = new HashMap<Long, Book>();
		addBook1();
		addBook2();
		addBook3();
		addBook4();
		addBook5();
	}

	private static void addBook5() {
		Book book = new BookBuilder().withAuthor("Jarek Kuczynski")
				.withTitle("Jak zalozyc konto bankowe.").withId(5L)
				.withImage(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
				.build();
		instance.books.put(book.getId(), book);
	}

	private static void addBook4() {
		Book book = new BookBuilder()
				.withAuthor("Kaczor Donald")
				.withTitle("Oficerki")
				.withId(4L)
				.withImage(
						new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 })
				.build();
		instance.books.put(book.getId(), book);
	}

	private static void addBook3() {
		Book book = new BookBuilder().withAuthor("Ludwik Born")
				.withTitle("O psie Sabie.").withId(3L)
				.withImage(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
				.build();
		instance.books.put(book.getId(), book);
	}

	private static void addBook2() {
		Book book = new BookBuilder().withAuthor("Anna Krajewska")
				.withTitle("Poznaj ten kraj.").withId(2L)
				.withImage(new byte[] { 1, 2, 3, 4, 5, 6, 7 }).build();
		instance.books.put(book.getId(), book);
	}

	private static void addBook1() {
		Book book = new BookBuilder().withAuthor("Zbigniew Czytelnik")
				.withTitle("Czytaj i pisz.").withId(1L)
				.withImage(new byte[] { 1, 2, 3, 4, 5 }).build();
		instance.books.put(book.getId(), book);
	}

	public Map<Long, Book> getBooks() {
		return books;
	}

	public String getOwner() {
		return owner;
	}
}
