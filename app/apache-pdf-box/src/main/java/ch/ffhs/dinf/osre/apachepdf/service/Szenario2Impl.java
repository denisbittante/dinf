package ch.ffhs.dinf.osre.apachepdf.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import ch.ffhs.dinf.osre.apachepdf.service.Utils.Style;
import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario2;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;

public class Szenario2Impl extends AbstractScenario<PdfRequestScenario2> implements Scenario2 {

	private static final int BORDER_BOTTOM = 30;
	private static final int BORDER_LEFT = 25;
	private static final int BORDER_RIGHT = 25;
	private static final int BORDER_TOP = 50;

	private static final int COL_1 = 50;
	private static final int COL_2 = 300;
	private static final int COL_3 = 100;

	private PDPageContentStream contentStream;
	private PDDocument document;
	private static final PDRectangle PAGESIZE = PDRectangle.A4;
	private PDDocumentInformation pdd;

	public Szenario2Impl(String name, String description) {
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
		setTitle();
		document.save(getTempfile());
		document.close();

		System.out.println("PDF created");

		// Closing the document

	}

	public int countPagesByInput() {
		int size = getModel().getGroup().size();
		float mod = size % 2;

		int countpages = size / 2;
		if (mod > 0) {
			countpages++;
		}

		return countpages;

	}

	@Override
	public void createChapters() throws Exception {

		for (int i = 0; i < getModel().getGroup().size(); i++) {

			ActivityGroup acGr = getModel().getGroup().get(i);
			PDPage page = document.getPage(i / 2);

			contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, false);

			contentStream.addRect(10, 10, 200, 300);
			contentStream.setNonStrokingColor(255, 100, 100);
			contentStream.fill();

			contentStream.beginText();

			if (i % 2 == 0) {
				contentStream.newLineAtOffset(BORDER_LEFT, PAGESIZE.getUpperRightY() - BORDER_TOP);
			} else {
				contentStream.newLineAtOffset(BORDER_LEFT, PAGESIZE.getUpperRightY() / 2);

			}
			contentStream.setLeading(15);
			insertText(acGr.getTitle(), FontType.H1);

			for (ActivityEntry entry : acGr.getEntries()) {
				contentStream.newLine();

				if (entry.isSubtitle()) {
					insertText(entry.getTitle(), FontType.H3);

				} else {
					insertText(entry.getTime(), FontType.bold_italic, COL_1);
					contentStream.newLineAtOffset(COL_1, 0f);
					insertText(entry.getTitle(), FontType.normal, COL_2);
					contentStream.newLineAtOffset(COL_2, 0f);
					insertText(entry.getPerson(), FontType.italic, COL_3);
					contentStream.newLineAtOffset(-(COL_1 + COL_2), 0f);
				}
			}
			contentStream.newLineAtOffset(0f, 0f);
			contentStream.newLine();
			insertText(acGr.getFooter(), FontType.small);
			contentStream.endText();
			contentStream.close();
		}

	}

	@Override
	public void setTitle() {
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
		contentStream.setNonStrokingColor(0, 0, 0);

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
