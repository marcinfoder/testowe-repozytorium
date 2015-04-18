package com.capgemini.rest.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.capgemini.persistence.domain.Book;
import com.capgemini.persistence.domain.Books;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

//itext5.x -> see http://www.codejava.net/frameworks/spring/spring-web-mvc-with-pdf-view-example-using-itext-5x
public class BookPdfView extends AbstractPdfView {

	private static BaseFont helvetica;
	private static Font normal;
	private static Font titleFont;

	static {
		try {
			helvetica = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1250, BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		normal = new Font(helvetica, 10, Font.NORMAL);
		titleFont = new Font(helvetica, 12, Font.NORMAL);
	}

	@Override
	protected void buildPdfDocument(@SuppressWarnings("rawtypes") Map model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Books books = (Books) model.get("books");

		addTitleSection(document);
		addOwnerSection(document, books.getOwner());

		PdfPTable table = new PdfPTable(2);

		table.addCell("Title");
		table.addCell("Author");

		for (Book book : books.getBooks()) {
			table.addCell(book.getTitle());
			table.addCell(book.getAuthor());
		}

		document.add(table);
	}

	private void addOwnerSection(Document document, String owner)
			throws DocumentException {
		if (StringUtils.isNotEmpty(owner)) {
			Paragraph p = new Paragraph("OWNER: " + owner, normal);
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			document.add(new Paragraph(" "));
		}
	}

	private void addTitleSection(Document document) throws DocumentException {
		Paragraph p = new Paragraph("BOOKS", titleFont);
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		document.add(new Paragraph(" "));
	}

}
