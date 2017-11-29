package ch.ffhs.dinf.osre.apachepdf.service;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

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
		for (int i = 0; i < 10; i++) {
			// Creating a blank page
			PDPage blankPage = new PDPage();

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
		document.save(new File("C:\\temp\\exampleapachepdfbox.pdf"));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setFont() throws Exception {
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	}

	PDPageContentStream contentStream;

	@Override
	public void createChapters() throws Exception {
		PDPage page = document.getPage(1);
		contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		setFont();
		contentStream.newLineAtOffset(25, 700);

		String text = "This is the sample document and we are adding content to it.";

		// Adding text in the form of string
		contentStream.showText(text);
		contentStream.endText();

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
