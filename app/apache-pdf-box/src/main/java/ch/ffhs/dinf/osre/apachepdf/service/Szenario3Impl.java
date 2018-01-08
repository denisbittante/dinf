package ch.ffhs.dinf.osre.apachepdf.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitRectangleDestination;
import org.apache.pdfbox.text.TextPosition;

import ch.ffhs.dinf.osre.apachepdf.service.Utils.Style;
import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario3;
import ch.ffhs.dinf.osre.engine.Scenario1.FontType;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.Contact;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;

public class Szenario3Impl extends AbstractScenario<PdfRequestScenario3> implements Scenario3 {

	private static final int BORDER_BOTTOM = 30;
	private static final int BORDER_LEFT = 25;
	private static final int BORDER_RIGHT = 25;
	private static final int BORDER_TOP = 50;

	private static final int COL_1 = 50;
	private static final int COL_2 = 50;
	private static final int COL_3 = 75;
	private static final int COL_4 = 75;
	private static final int COL_5 = 50;
	private static final int COL_6 = 75;
	private static final int COL_7 = 75;
	private static final int COL_8 = 75;
	private static final int COL_9 = 150;
	private static final int COL_10 = 75;
	private static final int COL_11 = 75;

	private PDPageContentStream contentStream;
	private PDDocument document;
	private static final PDRectangle PAGESIZE = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());;
	private PDDocumentInformation pdd;

	public Szenario3Impl(String name, String description) {
		super(name, description);
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

		document.save(getTempfile());
		document.close();

		System.out.println("PDF created");

		// Closing the document

	}

	public int countPagesByInput() {
		int size = getModel().getContacts().size();
		float mod = size % 20;

		int countpages = size / 20;
		if (mod > 0) {
			countpages++;
		}

		return countpages;

	}

	@Override
	public void createChapters() throws Exception {
		PDPage page = null;
		int currentPage = -1;

		for (int i = 0; i < getModel().getContacts().size(); i++) {
			Contact contact = getModel().getContacts().get(i);
			int pageIndex = i / 20;

			if (pageIndex != currentPage) {
				currentPage = pageIndex;
				page = document.getPage(currentPage);
				contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, false);
			}

			contentStream.setLeading(15);
			if (i == 0) {
				contentStream.beginText();
				contentStream.newLineAtOffset(BORDER_LEFT, PAGESIZE.getUpperRightY() - BORDER_TOP);
				insertText("Kontaktliste", FontType.H1);
				contentStream.endText();
				contentStream.beginText();
				contentStream.newLineAtOffset(BORDER_LEFT, PAGESIZE.getUpperRightY() - BORDER_TOP - 50);
				contentStream.newLine();
			} else {
				contentStream.beginText();
				contentStream.newLineAtOffset(BORDER_LEFT, PAGESIZE.getUpperRightY() - BORDER_TOP - 50 - (i * 20));
				contentStream.newLine();
			}

			insertText(contact.getName(), FontType.small, COL_1);
			contentStream.newLineAtOffset(COL_1, 0f);

			insertText(contact.getFirstname(), FontType.small, COL_2);
			contentStream.newLineAtOffset(COL_2, 0f);

			insertText(contact.getAddress(), FontType.small, COL_3);
			contentStream.newLineAtOffset(COL_3, 0f);

			insertText(contact.getZip(), FontType.small, COL_4);
			contentStream.newLineAtOffset(COL_4, 0f);

			insertText(contact.getPlace(), FontType.small, COL_5);
			contentStream.newLineAtOffset(COL_5, 0f);

			insertText(contact.getTel(), FontType.small, COL_6);
			contentStream.newLineAtOffset(COL_6, 0f);

			insertText(contact.getMob(), FontType.small, COL_7);
			contentStream.newLineAtOffset(COL_7, 0f);

			insertText(contact.getBirthday(), FontType.small, COL_8);
			contentStream.newLineAtOffset(COL_8, 0f);

			insertText(contact.getNote(), FontType.small, COL_9);
			contentStream.newLineAtOffset(COL_9, 0f);

			insertText(contact.getEmail(), FontType.small, COL_10);
			contentStream.newLineAtOffset(COL_10, 0f);

			insertText(contact.getGender(), FontType.small, COL_11);
			contentStream.newLineAtOffset(-(900f), 0f);
			contentStream.newLine();

			contentStream.endText();

		}

		int strokeInitYHeight = 0;
		int strokeYHeight = 0;

		for (int i = 0; i < getModel().getContacts().size(); i++) {

			// Draws Table

			// next line at
			if (i < 20) {
				// add space for the title
				strokeInitYHeight = -(50 + BORDER_TOP);
			} else {
				strokeInitYHeight = -BORDER_TOP;
			}
			strokeYHeight = (int) (strokeInitYHeight + PAGESIZE.getUpperRightY() - (i * 20));
			contentStream.moveTo(BORDER_RIGHT, strokeYHeight);
			contentStream.lineTo(PAGESIZE.getUpperRightX() - BORDER_LEFT, strokeYHeight);
			contentStream.stroke();
		}

		// vertical line
		contentStream.moveTo(BORDER_LEFT, strokeInitYHeight);
		contentStream.lineTo(PAGESIZE.getUpperRightX() - BORDER_LEFT, strokeYHeight);
		contentStream.stroke();

		// addMaleGenderSign();
		contentStream.close();

	}

	private void addMaleGenderSign() throws IOException {
		String path = getClass().getResource("/male.jpg").getPath();
		System.out.println(path);
		PDImageXObject pdImage = PDImageXObject.createFromFile(path, document);

		contentStream.drawImage(pdImage, 100, 100, 20, 20);
	}

	@Override
	public void createTitle() {
		pdd.setTitle(SUBJECT);
	}

	private void insertText(String string, FontType t) throws Exception {
		insertText(string, t, 500);
	}

	private void insertText(String string, FontType t, float columnsize) throws Exception {
		setFont(t);
		String[] strings = Utils.makeSubtextByFontSize(string, Style.valueOf(t.name()).fontsize, columnsize,
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

}
