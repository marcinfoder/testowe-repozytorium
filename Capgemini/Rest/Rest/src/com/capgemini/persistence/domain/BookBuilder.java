package com.capgemini.persistence.domain;

public class BookBuilder {

	private Book book = new Book();

	public Book build() {
		return book;
	}

	public BookBuilder withId(Long id) {
		book.setId(id);
		return this;
	}

	public BookBuilder withAuthor(String author) {
		book.setAuthor(author);
		return this;
	}

	public BookBuilder withTitle(String title) {
		book.setTitle(title);
		return this;
	}

	public BookBuilder withImage(byte[] image) {
		book.setImage(image);
		return this;
	}
}
