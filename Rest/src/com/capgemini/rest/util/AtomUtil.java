package com.capgemini.rest.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;

public class AtomUtil {

	private static final String TITLE = "Book Atom Feed";
	private static final String FEED_TYPE = "atom_1.0";
	private static AtomUtil instance;

	public static final AtomUtil instance() {
		if (instance == null) {
			instance = new AtomUtil();
		}
		return instance;
	}

	private AtomUtil() {

	}

	public Feed bookFeed(Books books, Jaxb2Marshaller marshaller) {
		Feed feed = createFeed();
		List<Entry> entries = createEntries(books, marshaller);
		feed.setEntries(entries);
		return feed;
	}

	private Feed createFeed() {
		Feed feed = new Feed();
		feed.setFeedType(FEED_TYPE);
		feed.setTitle(TITLE);
		return feed;
	}

	private List<Entry> createEntries(Books books, Jaxb2Marshaller marshaller) {
		List<Entry> entries = new ArrayList<Entry>();
		for (Book e : books.getBooks()) {
			String xml = createXML(marshaller, e);
			Entry entry = createEntry(e);
			Content content = createContent(xml);
			addContentToEntry(entry, content);
			entries.add(entry);
		}
		return entries;
	}

	private String createXML(Jaxb2Marshaller marshaller, Book e) {
		StreamResult result = new StreamResult(new ByteArrayOutputStream());
		marshaller.marshal(e, result);
		String xml = result.getOutputStream().toString();
		return xml;
	}

	private void addContentToEntry(Entry entry, Content content) {
		List<Content> contents = new ArrayList<Content>();
		contents.add(content);
		entry.setContents(contents);
	}

	private Content createContent(String xml) {
		Content content = new Content();
		content.setType(Content.XML);
		content.setValue(xml);
		return content;
	}

	private Entry createEntry(Book e) {
		Entry entry = new Entry();
		entry.setId(Long.valueOf(e.getId()).toString());
		entry.setTitle(e.getTitle());
		return entry;
	}
}
