package com.capgemini.rest.controller;

import java.util.Collections;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.persistence.IBookDao;
import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;
import com.capgemini.rest.client.IConstrainedBookService;
import com.capgemini.rest.model.SearchForm;
import com.capgemini.service.IBookService;

@Controller
@SessionAttributes("searchForm")
public class BookController {

	@Resource(name = "bookDao")
	private IBookDao bookDao;

	@Autowired
	private IBookService service;

	@Autowired
	private Jaxb2Marshaller marshaller;

	@Autowired
	private IConstrainedBookService constrainedBookService;

	private static final String VIEW_NAME = "books";

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public String setupForm(Model model) {
		model.addAttribute("searchForm", new SearchForm());
		return "search";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/exception")
	public String exception(Model model) {
		throw new RuntimeException("sample exception");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/search2")
	public ModelAndView doSearch(SearchForm search, BindingResult bindingResult) {
		Books books = service.findBooks(search.getAuthor(), search.getTitle());
		return new ModelAndView(VIEW_NAME, "books", books);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public String search(
			@Valid @ModelAttribute("searchForm") SearchForm search,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "search";
		}
		Books books = service.findBooks(search.getAuthor(), search.getTitle());
		model.addAttribute("books", books);
		model.addAttribute(
				"constrainedBooks",
				constrainedBookService.findBooks(search.getAuthor(),
						search.getTitle()));
		return "myBooks"; // przekierowanie na myBooks.jsp
	}

	@RequestMapping(method = RequestMethod.GET, value = "/booksExt")
	public String books(ModelMap model) {
		model.put("books", bookDao.list());
		return "myBooks";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/booksExt/{format}/{id}")
	public String booksWithFormat(ModelMap model,
			@PathVariable(value = "format") String format,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "param", required = false) String param) {
		if ("all".equals(id)) {
			model.put("books", bookDao.list());
		} else {
			Book book = bookDao.getWith(Long.valueOf(id));
			model.put("books", new Books(Collections.singletonList(book),
					StringUtils.EMPTY));
		}
		System.out.println(param);
		if ("pdf".equals(format.toLowerCase())) {
			return "booksPdfView";
		}
		if ("xml".equals(format.toLowerCase())) {
			return "booksXmlView";
		}
		if ("json".equals(format.toLowerCase())) {
			return "booksJsonView";
		}
		return "myBooks";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showBook/{bookId}")
	public String getBook(@PathVariable(value = "bookId") Long bookId,
			ModelMap model) {
		Book book = service.findById(bookId);
		model.put("book", book);
		return "myBook";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showBooks")
	public String getBooks(@ModelAttribute("searchForm") SearchForm searchForm,
			ModelMap model) {
		Books books = service.findBooks(searchForm.getAuthor(),
				searchForm.getTitle());
		model.addAttribute("books", books);
		model.addAttribute("constrainedBooks", constrainedBookService
				.findBooks(searchForm.getAuthor(), searchForm.getTitle()));
		return "myBooks"; // przekierowanie na myBooks.jsp
	}
}
