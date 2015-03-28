package com.capgemini.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.context.AppContext;
import com.capgemini.service.IBookService;

public class InvokeJob {

	@Autowired
	private IBookService bookService;

	public void run() {
		System.out.println(AppContext
				.getBean("bookService", IBookService.class).findAll()
				.getBooks());
		System.out.println("invoke run");
	}
}
