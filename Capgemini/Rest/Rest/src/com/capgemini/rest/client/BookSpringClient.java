package com.capgemini.rest.client;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.context.AppContext;
import com.capgemini.rest.model.SearchForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(inheritLocations = true, locations = { "file:webapp/WEB-INF/applicationContext.xml" })
public class BookSpringClient {

	@Autowired
	Validator validator;

	@Resource(name = "jsonList")
	private IRequest listJsonRequest;

	@Resource(name = "xmlList")
	private IRequest listXmlRequest;

	@Resource(name = "jsonListHttp")
	private IRequest listJsonHttpRequest;

	@Resource(name = "xmlListHttp")
	private IRequest listXmlHttpRequest;

	@Resource(name = "xmlAtomListHttp")
	private IRequest listXmlAtomRequest;

	@Resource(name = "xmlAtomList")
	private IRequest listXmlAtomHttpRequest;

	@Resource(name = "newBookJson2XmlHttp")
	private IRequest newBookJson2XmlHttpRequest;

	@Resource(name = "newBookXml2JsonHttp")
	private IRequest newBookXml2JsonHttpRequest;

	@Resource(name = "htmlListHttp")
	private IRequest listHtmlHttpRequest;

	@Resource(name = "htmlList")
	private IRequest listHtmlRequest;

	@Resource(name = "newBookJson2Xml")
	private IRequest newBookJson2XmlRequest;

	@Resource(name = "newBookXml2Json")
	private IRequest newBookXml2JsonRequest;

	@Resource(name = "newBookString2Xml")
	private IRequest newBookString2XmlRequest;

	@Resource(name = "newBookString2XmlHttp")
	private IRequest newBookString2XmlHttpRequest;

	@Resource(name = "jsonBook")
	private IRequest getJsonRequest;

	@Resource(name = "xmlBook")
	private IRequest getXmlRequest;

	@Resource(name = "xmlRemoveBook")
	private IRequest removeXmlRequest;

	@Resource(name = "jsonRemoveBook")
	private IRequest removeJsonRequest;

	@Resource(name = "updateJsonBook")
	private IRequest updateJsonRequest;

	@Resource(name = "updateXmlBook")
	private IRequest updateXmlRequest;

	@Resource(name = "jsonBookHttp")
	private IRequest getJsonHttpRequest;

	@Resource(name = "xmlBookHttp")
	private IRequest getXmlHttpRequest;

	@Resource(name = "xmlRemoveBookHttp")
	private IRequest removeXmlHttpRequest;

	@Resource(name = "jsonRemoveBookHttp")
	private IRequest removeJsonHttpRequest;

	@Resource(name = "updateJsonBookHttp")
	private IRequest updateJsonHttpRequest;

	@Resource(name = "updateXmlBookHttp")
	private IRequest updateXmlHttpRequest;

	@Resource(name = "getImage")
	private IRequest imageRequest;

	@Resource(name = "addImage")
	private IRequest addImageRequest;

	@Test
	public void listJson() throws Exception {
		title("JSON LIST");
		listJsonRequest.execute();
	}

	@Test
	public void listXml() throws Exception {
		title("XML LIST");
		listXmlRequest.execute();
	}

	@Test
	public void listJsonHttp() throws Exception {
		title("JSON HTTP LIST");
		listJsonHttpRequest.execute();
	}

	@Test
	public void listXmlHttp() throws Exception {
		title("XML HTTP LIST");
		listXmlHttpRequest.execute();
	}

	@Test
	public void listXmlAtom() throws Exception {
		title("XML ATOM LIST");
		listXmlAtomRequest.execute();
	}

	@Test
	public void listXmlAtomHttp() throws Exception {
		title("XML ATOM HTTP LIST");
		listXmlAtomHttpRequest.execute();
	}

	@Test
	public void newBookJson2XmlHttp() throws Exception {
		title("JSON TO XML NEW BOOK HTTP");
		newBookJson2XmlHttpRequest.execute();
	}

	@Test
	public void newBookXml2JsonHttp() throws Exception {
		title("XML TO JSON NEW BOOK HTTP");
		newBookXml2JsonHttpRequest.execute();
	}

	@Test
	public void listHtmlHttp() throws Exception {
		title("HTML HTTP LIST");
		listHtmlHttpRequest.execute();
	}

	@Test
	public void listHtml() throws Exception {
		title("HTML LIST");
		listHtmlRequest.execute();
	}

	@Test
	public void newBookJson2Xml() throws Exception {
		title("JSON TO XML NEW BOOK");
		newBookJson2XmlRequest.execute();
	}

	@Test
	public void newBookXml2Json() throws Exception {
		title("XML TO JSON NEW BOOK");
		newBookXml2JsonRequest.execute();
	}

	@Test
	public void newBookString2Xml() throws Exception {
		title("STRING TO XML NEW BOOK");
		newBookString2XmlRequest.execute();
	}

	@Test
	public void newBookString2XmlHttp() throws Exception {
		title("STRING TO XML NEW BOOK HTTP");
		newBookString2XmlHttpRequest.execute();
	}

	@Test
	public void getJson() throws Exception {
		title("GET JSON BOOK");
		getJsonRequest.execute();
	}

	@Test
	public void getXml() throws Exception {
		title("GET XML BOOK");
		getXmlRequest.execute();
	}

	@Test
	public void removeXml() throws Exception {
		title("REMOVE XML BOOK");
		removeXmlRequest.execute();
	}

	@Test
	public void removeJson() throws Exception {
		title("REMOVE JSON BOOK");
		removeJsonRequest.execute();
	}

	@Test
	public void updateJson() throws Exception {
		title("UPDATE JSON BOOK");
		updateJsonRequest.execute();
	}

	@Test
	public void updateXml() throws Exception {
		title("UPDATE XML BOOK");
		updateXmlRequest.execute();
		listJsonRequest.execute();
	}

	@Test
	public void getJsonHttp() throws Exception {
		title("GET JSON HTTP BOOK");
		getJsonHttpRequest.execute();
	}

	@Test
	public void getXmlHttp() throws Exception {
		title("GET XML BOOK HTTP");
		getXmlHttpRequest.execute();
	}

	@Test
	public void removeXmlHttp() throws Exception {
		title("REMOVE XML HTTP BOOK");
		removeXmlHttpRequest.execute();
	}

	@Test
	public void removeJsonHttp() throws Exception {
		title("REMOVE JSON BOOK HTTP");
		removeJsonHttpRequest.execute();
	}

	@Test
	public void updateJsonHttp() throws Exception {
		title("UPDATE JSON HTTP BOOK");
		updateJsonHttpRequest.execute();
		listJsonRequest.execute();
	}

	@Test
	public void updateXmlHttp() throws Exception {
		title("UPDATE XML HTTP BOOK");
		updateXmlHttpRequest.execute();
		listJsonRequest.execute();
	}

	@Test
	public void image() throws Exception {
		title("IMAGE");
		imageRequest.execute();
	}

	@Test
	public void addImage() throws Exception {
		title("ADD IMAGE");
		addImageRequest.execute();
	}

	@Test
	public void validate() {
		/*
		 * ResourceBundle b = ResourceBundle.getBundle("messages");
		 * System.out.println(b.getKeys()); ResourceBundleMessageSource messages
		 * = AppContext .getBean( "messageSource",
		 * org.springframework.context.support
		 * .ResourceBundleMessageSource.class);
		 * System.out.println(messages.getMessage("search.my.incorrectTitle",
		 * new Object[] { "this incorect title" }, "default message",
		 * Locale.ENGLISH));
		 */
		SearchForm form = new SearchForm();
		form.setAuthor("mysz");
		form.setTitle("ugly");

		validator.validate(form);

		Set<ConstraintViolation<SearchForm>> constraintViolations = validator
				.validate(form);
		for (ConstraintViolation<SearchForm> violation : constraintViolations) {
			System.out.println(violation.getPropertyPath().toString());
			System.out.println(violation.getMessageTemplate());
		}
	}

	private static void title(String title) {
		System.out.println("\n************");
		System.out.println(title);
	}
	
	@Test
	public void pppppppppppppt() {		
		DaoAuthenticationProvider a = AppContext.getBean("daoAuthenticationProvider", DaoAuthenticationProvider.class);
		UsernamePasswordAuthenticationToken ll = new  UsernamePasswordAuthenticationToken("rod", "koala");
		Authentication aa = a.authenticate(ll);
		System.out.println(aa);
	}
}