package ch.ffhs.dinf.osre.apachepdf.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import ch.ffhs.dinf.osre.apachepdf.service.Utils.Style;
import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario1;
import ch.ffhs.dinf.osre.engine.api.ActivityDetails;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;

public class Szenario1ApacheImpl extends AbstractScenario<PdfRequestScenario1> implements Scenario1 {

	private static final int BORDER_BOTTOM = 30;
	private static final int BORDER_LEFT = 25;
	private static final int BORDER_RIGHT = 25;
	private static final int BORDER_TOP = 50;
	private PDPageContentStream contentStream;
	private PDDocument document;
	private static final PDRectangle PAGESIZE = PDRectangle.A4;

	private PDDocumentInformation pdd;

	public Szenario1ApacheImpl(String name, String string) {
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
			PDPage blankPage = new PDPage(PAGESIZE);

			// Adding the blank page to the document
			document.addPage(blankPage);
		}
		setAuthor();
		setSubject();
		createChapters();
		setSubject();
		setKeywords();
		setTitle();

		document.save(getTempfile());
		document.close();

		System.out.println("PDF created");

		// Closing the document

	}

	public int countPagesByInput() {

		if (getModel() != null) {
			return getModel().getData().size();
		} else {
			return 0;
		}
	}

	@Override
	public void createChapters() throws Exception {

		for (int i = 0; i < countPagesByInput(); i++) {

			ActivityDetails aD = getModel().getData().get(i);

			PDPage page = document.getPage(i);

			contentStream = new PDPageContentStream(document, page);

			contentStream.beginText();

			contentStream.newLineAtOffset(BORDER_LEFT, PDRectangle.A4.getUpperRightY() - BORDER_TOP);
			contentStream.setLeading(15);
			// Adding text in the form of string
			insertText(aD.getTitle(), FontType.H1);
			contentStream.newLine();
			contentStream.newLine();
			insertText(aD.getDatumVon(), FontType.H2);
			insertText(" - ", FontType.H2);
			insertText(aD.getDatumBis(), FontType.H2);
			contentStream.newLine();
			insertText(aD.getDescription());
			contentStream.newLine();

			insertText("Ort", FontType.bold);
			contentStream.newLine();
			insertText(aD.getPlace());
			contentStream.newLine();
			insertText("Verantwortlicher ", FontType.bold);
			contentStream.newLine();
			insertText(aD.getIncharge());
			contentStream.newLine();
			insertText("Helfer ", FontType.bold);
			contentStream.newLine();
			insertText(aD.getHelper());
			contentStream.newLine();
			contentStream.endText();
			// Footer

			contentStream.beginText();
			contentStream.newLineAtOffset(25, PAGESIZE.getLowerLeftY() + BORDER_BOTTOM);
			insertText("Erstellt von : ", FontType.small);
			insertText(aD.getAuthor(), FontType.small);
			contentStream.endText();

			contentStream.beginText();
			contentStream.newLineAtOffset(PAGESIZE.getUpperRightX() / 2, PAGESIZE.getLowerLeftY() + BORDER_BOTTOM);
			insertText(i + 1 + "/", FontType.small);
			insertText(String.valueOf(countPagesByInput()), FontType.small);
			contentStream.endText();

			contentStream.beginText();
			String erstelldatum = new Date().toString();
			contentStream.newLineAtOffset(PAGESIZE.getUpperRightX() - 150, PAGESIZE.getLowerLeftY() + BORDER_BOTTOM);
			insertText(erstelldatum, FontType.small);
			contentStream.endText();
			contentStream.close();
		}

	}

	private void insertText(String string) throws Exception {
		insertText(string, FontType.normal);
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
	public void setAuthor() {
		pdd.setAuthor(AUTHOR);
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

	@Override
	public void setKeywords() {
		pdd.setKeywords(KEYWORDS);
	}

	@Override
	public void setSubject() {
		pdd.setSubject(SUBJECT);
	}

	@Override
	public void setTitle() {
		pdd.setTitle(SUBJECT);

	}

}
