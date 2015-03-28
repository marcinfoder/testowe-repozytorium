package com.capgemini.rest.client;

public class RequestUtils {

	private String mainUrl;

	public static final String URL_LIST_HTML = "booksHtml";

	public static final String URL_LIST = "books";

	public static final String URL_BOOK = "book";

	public static final String URL_BOOK_STRING = "bookAsString";

	public static final String URL_IMAGE = "image";

	private static final String ID_PARAM = "/{id}";

	private static final String SEPARATOR = "/";

	public RequestUtils() {

	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public final String getUrl(String action) {
		return mainUrl + action;
	}

	public String getUrlWithId(String action) {
		return mainUrl + action + ID_PARAM;
	}

	public String getUrlWithValue(String action, Object value) {
		return mainUrl + action + SEPARATOR + value;
	}
}
