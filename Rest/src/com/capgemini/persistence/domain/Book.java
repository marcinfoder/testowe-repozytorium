package com.capgemini.persistence.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

	public static final String PROP_AUTHOR = "author";
	
	@XmlAttribute(name = "id")
	private long id;
	@JsonProperty(value = "jsonTitle")
	private String title;
	private String author;
	private String authorAddress;
	@XmlTransient
	private byte[] image;

	public Book() {

	}

	public Book(long id, String title, String author, String authorAddress,
			byte[] image) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.authorAddress = authorAddress;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getAuthorAddress() {
		return authorAddress;
	}

	public void setAuthorAddress(String authorAddress) {
		this.authorAddress = authorAddress;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		value.append("id: ");
		value.append(id);
		value.append(", title: ");
		value.append(title);
		value.append(", author: ");
		value.append(author);
		value.append(", image: (");
		if (image != null) {
			for (int i = 0; i < image.length; i++) {
				value.append(image[i]);
				if (i < image.length - 1) {
					value.append(", ");
				}
			}
		}
		value.append(")");
		return value.toString();
	}

}
