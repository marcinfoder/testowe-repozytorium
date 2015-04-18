package com.capgemini.rest.controller;

import java.io.StringReader;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;
import com.capgemini.rest.client.IConstrainedBookService;
import com.capgemini.rest.util.AtomUtil;
import com.capgemini.service.IBookService;
import com.sun.syndication.feed.atom.Feed;

@Controller
public class BookRestController {

	@Resource(name = "bookDao")
	private IBookDao bookDao;

	@Autowired
	private IBookService service;

	@Autowired
	private Jaxb2Marshaller marshaller;

	@Autowired
	private IConstrainedBookService constraineBookService;

	@RequestMapping(method = RequestMethod.POST, value = "/bookAsString")
	public ModelAndView addBook(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Book book = (Book) marshaller.unmarshal(source);
		bookDao.add(book);
		return new ModelAndView("booksXmlView", "object", book);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/booksHtml")
	public ModelAndView getAllBooks() {
		Books books = bookDao.list();
		return new ModelAndView("myBooks", "books", books);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/booksXML", headers = "Accept=application/xml")
	public ModelAndView getAllBooksAsXML() {
		Books books = bookDao.list();
		return new ModelAndView("booksXmlView", "books", books);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/book/{id}", headers = "Accept=application/json, application/xml")
	public @ResponseBody
	Book getBook(@PathVariable Long id) {
		return bookDao.getWith(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books", headers = "Accept=application/json, application/xml")
	public @ResponseBody
	Books getAll() {
		return bookDao.list();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books", headers = "Accept=application/atom+xml")
	public @ResponseBody
	Feed getBookFeed() {
		Books books = bookDao.list();
		return AtomUtil.instance().bookFeed(books, marshaller);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public @ResponseBody Book addBook(@RequestBody Book book) {
		bookDao.add(book);
		return book;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/book")
	public @ResponseBody
	Book update(@RequestBody Book book) {
		bookDao.update(book);
		return book;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/book/{id}")
	public @ResponseBody
	Book remove(@PathVariable Long id) {
		return bookDao.deleteWith(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sampleText", headers = "Accept=plain/text")
	public @ResponseBody
	String getSampleText() {
		return "sample Text fdnslkgndslvmsfkapskf lmlsamdlasd,as";
	}

}

/*
 * @RequestMapping(value = "/student", method = RequestMethod.GET) public
 * ModelAndView student() { return new ModelAndView("student", "command", new
 * Student()); }
 * 
 * @RequestMapping(value = "/addStudent", method = RequestMethod.POST) public
 * String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap
 * model) { model.addAttribute("name", student.getName());
 * model.addAttribute("age", student.getAge()); model.addAttribute("id",
 * student.getId());
 * 
 * return "result"; }
 */