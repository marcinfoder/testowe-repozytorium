package com.capgemini.rest.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.capgemini.rest.model.validator.CorrectTitle;

public class SearchForm {

	@Size(min = 1)
	@Pattern(regexp = "[A-Za-z]*")
	private String author;

	@NotEmpty
	@Size(max = 20)
	@CorrectTitle(incorrectTitle = "ugly")
	private String title;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
