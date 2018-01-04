package ch.ffhs.dinf.osre.apachepdf.service;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ch.ffhs.dinf.osre.apachepdf.service.Utils.*;
import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario1;

public class Szenario1Impl extends AbstractScenario implements Scenario1 {

	private PDDocumentInformation pdd;
	private PDDocument document;

	public Szenario1Impl(String name, String string) {
		super(name, string);
	}

	@Override
	protected void buildPdf() throws Exception {

		// Creating PDF document object
		document = new PDDocument();
		pdd = document.getDocumentInformation();

		// Saving the document
		for (int i = 0; i < countPagesByInput(); i++) {
			// Creating a blank page
			PDPage blankPage = new PDPage(PDRectangle.A4);

			// Adding the blank page to the document
			document.addPage(blankPage);
		}

		setAuthor();
		setSubject();
		setFileName();
		setPageSize();
		createChapters();
		setSubject();
		setKeywords();

		document.save(getTempfile());
		document.close();

		System.out.println("PDF created");

		// Closing the document

	}

	@Override
	public void setAuthor() {
		pdd.setAuthor(AUTHOR);
	}

	@Override
	public void setFileName() {
	}

	@Override
	public void setFont(FontType t) throws Exception {
		switch (t) {
		case H1:
			contentStream.setFont(Style.H1.font, Style.H1.fontsize);
			break;
		case H2:
			contentStream.setFont(Style.H2.font, Style.H2.fontsize);
			break;
		case H3:
			contentStream.setFont(Style.H3.font, Style.H3.fontsize);
			break;
		case bold:
			contentStream.setFont(Style.bold.font, Style.bold.fontsize);
			break;
		case italic:
			contentStream.setFont(Style.italic.font, Style.italic.fontsize);
			break;
		case bold_italic:
			contentStream.setFont(Style.bold_italic.font, Style.bold_italic.fontsize);
			break;
		case small:
			contentStream.setFont(Style.small.font, Style.small.fontsize);
			break;
		default:
			contentStream.setFont(Style.normal.font, Style.normal.fontsize);
			break;
		}
	}

	PDPageContentStream contentStream;

	@Override
	public void createChapters() throws Exception {

		for (int i = 0; i < countPagesByInput(); i++) {

			PDPage page = document.getPage(i);
			contentStream = new PDPageContentStream(document, page);
			contentStream.beginText();

			contentStream.newLineAtOffset(25, 800);
			contentStream.setLeading(15);
			// Adding text in the form of string
			insertText(i, "title", FontType.H1);
			contentStream.newLine();
			insertText(i, "datum-von", FontType.H2);
			insertText(" - ", FontType.H2);
			insertText(i, "datum-bis", FontType.H2);
			contentStream.newLine();
			insertText(i, "description");
			contentStream.newLine();

			insertText("Ort", FontType.bold);
			contentStream.newLine();
			insertText(i, "place");
			contentStream.newLine();
			insertText("Verantwortlicher ", FontType.bold);
			contentStream.newLine();
			insertText(i, "incharge");
			contentStream.newLine();
			insertText("Helfer ", FontType.bold);
			contentStream.newLine();
			insertText(i, "helper");
			contentStream.newLine();
			contentStream.endText();
			// Footer

			contentStream.beginText();
			contentStream.newLineAtOffset(25, PDRectangle.A4.getLowerLeftY() + 30);
			insertText("Erstellt von : ", FontType.small);
			insertText(i, "author", FontType.small);
			contentStream.endText();

			contentStream.beginText();
			contentStream.newLineAtOffset(PDRectangle.A4.getUpperRightX() / 2, PDRectangle.A4.getLowerLeftY() + 30);
			insertText(i + 1 + "/", FontType.small);
			insertText(String.valueOf(countPagesByInput()), FontType.small);
			contentStream.endText();

			contentStream.beginText();
			contentStream.newLineAtOffset(PDRectangle.A4.getUpperRightX() - 150, PDRectangle.A4.getLowerLeftY() + 30);
			insertText(new Date().toString(), FontType.small);
			contentStream.endText();
			contentStream.close();
		}

	}

	private void insertText(int i, String key) throws Exception {
		insertText(input(i, key));
	}

	private void insertText(String string) throws Exception {
		insertText(string, FontType.normal);
	}

	private void insertText(int i, String key, FontType t) throws Exception {
		insertText(input(i, key), t);
	}

	private void insertText(String string, FontType t) throws Exception {
		setFont(t);
		String[] strings = Utils.makeSubtextByFontSize(string, Style.valueOf(t.name()).fontsize, 500,
				Style.valueOf(t.name()).font);

		for (int i = 0; i < strings.length; i++) {
			contentStream.showText(strings[i]);
			if (i != strings.length - 1) {
				contentStream.newLine();
			}

		}
	}

	@Override
	public void setPageSize() {

	}

	@Override
	public void setSubject() {
		pdd.setSubject(SUBJECT);
	}

	@Override
	public void setKeywords() {
		pdd.setKeywords(KEYWORDS);
	}

}
