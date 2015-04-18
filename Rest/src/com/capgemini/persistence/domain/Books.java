package com.capgemini.persistence.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class Books {

	private String owner;
	private Integer count;
	private List<Book> books;

	public Books() {

	}

	public Books(List<Book> books, String owner) {
		this.books = books;
		this.owner = owner;
		this.count = books.size();
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		value.append("books\n");
		value.append("owner: ");
		value.append(owner);
		value.append(", book count: ");
		value.append(count);
		value.append("\nbooks\n");
		for (Book book : books) {
			value.append(book);
			value.append("\n");
		}
		return value.toString();
	}
}
