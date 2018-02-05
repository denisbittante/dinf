package ch.ffhs.dinf.osre.apachepdf.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import ch.ffhs.dinf.osre.apachepdf.service.Utils.Style;
import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario3;
import ch.ffhs.dinf.osre.engine.api.Contact;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;

public class Szenario3ApacheImpl extends AbstractScenario<PdfRequestScenario3> implements Scenario3 {

	private static final int BORDER_BOTTOM = 30;
	private static final int BORDER_LEFT = 25;
	private static final int BORDER_RIGHT = 25;
	private static final int BORDER_TOP = 50;

	private static final int DATAROWSPERPAGE = 20;
	private static final int ROWHEIGHT = 20;

	private int[] COL_SIZE = new int[] { 50, 50, 75, 30, 70, 70, 70, 40, 180, 120, 37 };

	private PDPageContentStream contentStream;
	private PDDocument document;
	private static final PDRectangle PAGESIZE = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());;
	private PDDocumentInformation pdd;

	public Szenario3ApacheImpl(String name, String description) {
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

		try {
		createChapters();
		setAuthor();
		setSubject();
		setSubject();
		setKeywords();
		setTitle();

		document.save(getTempfile());
		document.close();
		} catch (Exception e) {

			System.err.println("Error:" + e.getMessage());
		}

	}

	public int countPagesByInput() {
		int size = getModel().getContacts().size();
		float mod = size % DATAROWSPERPAGE;

		int countpages = size / DATAROWSPERPAGE;
		if (mod > 0) {
			countpages++;
		}

		return countpages;

	}

	@Override
	public void createChapters() throws Exception {
		PDPage page = null;
		int currentPage = -1;
		int lowerY = 0;
		for (int i = 0; i < getModel().getContacts().size(); i++) {
			Contact contact = getModel().getContacts().get(i);
			int pageIndex = i / DATAROWSPERPAGE;

			if (pageIndex != currentPage) {

				if (contentStream != null) {
					drawLastTableRowBorder(DATAROWSPERPAGE, lowerY);
					contentStream.close();
				}

				lowerY = 0;
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
				contentStream.newLineAtOffset(BORDER_LEFT,
						PAGESIZE.getUpperRightY() - BORDER_TOP - 50 - ((i % DATAROWSPERPAGE) * ROWHEIGHT));
				contentStream.newLine();
			}

			insertText(contact.getName(), FontType.small, COL_SIZE[0]);
			contentStream.newLineAtOffset(COL_SIZE[0], 0f);

			insertText(contact.getFirstname(), FontType.small, COL_SIZE[1]);
			contentStream.newLineAtOffset(COL_SIZE[1], 0f);

			insertText(contact.getAddress(), FontType.small, COL_SIZE[2]);
			contentStream.newLineAtOffset(COL_SIZE[2], 0f);

			insertText(contact.getZip(), FontType.small, COL_SIZE[3]);
			contentStream.newLineAtOffset(COL_SIZE[3], 0f);

			insertText(contact.getPlace(), FontType.small, COL_SIZE[4]);
			contentStream.newLineAtOffset(COL_SIZE[4], 0f);

			insertText(contact.getTel(), FontType.small, COL_SIZE[5]);
			contentStream.newLineAtOffset(COL_SIZE[5], 0f);

			insertText(contact.getMob(), FontType.small, COL_SIZE[6]);
			contentStream.newLineAtOffset(COL_SIZE[6], 0f);

			insertText(contact.getBirthday(), FontType.small, COL_SIZE[7]);
			contentStream.newLineAtOffset(COL_SIZE[7], 0f);

			insertText(contact.getNote(), FontType.small, COL_SIZE[8]);
			contentStream.newLineAtOffset(COL_SIZE[8], 0f);

			insertText(contact.getEmail(), FontType.small, COL_SIZE[9]);
			contentStream.newLineAtOffset(COL_SIZE[9], 0f);

			contentStream.newLineAtOffset(-(900f), 0f);
			contentStream.newLine();

			contentStream.endText();

			addGenderSign(i % DATAROWSPERPAGE);

			lowerY = drawHorizontalLines(i % DATAROWSPERPAGE);
			drawVerticalLine(i % DATAROWSPERPAGE, lowerY);

		}

		drawLastTableRowBorder(getModel().getContacts().size() + 1, lowerY);
		contentStream.close();

	}

	private void drawLastTableRowBorder(int i, int lowerY) throws IOException {
		lowerY = drawHorizontalLines(i % 21);
		drawVerticalLine(i % DATAROWSPERPAGE + 1, lowerY);

	}

	private int drawHorizontalLines(int i) throws IOException {
		int strokeInitYHeight;
		// Draw Horizontal lines

		// Draws Table

		// next line at
		if (i <= DATAROWSPERPAGE) {
			// add space for the title
			strokeInitYHeight = -(50 + BORDER_TOP);
		} else {
			strokeInitYHeight = -BORDER_TOP;
		}
		int lowerY = (int) (strokeInitYHeight + PAGESIZE.getUpperRightY() - (i * ROWHEIGHT));
		//System.out.println("lowY " + lowerY);
		contentStream.moveTo(BORDER_RIGHT, lowerY);
		contentStream.lineTo(PAGESIZE.getUpperRightX() - BORDER_LEFT, lowerY);
		contentStream.stroke();
		return lowerY;
	}

	private void drawVerticalLine(int i, int lowerY) throws IOException {
		// Draw vertical line
		float floatingX = BORDER_LEFT;
		float upperY = PAGESIZE.getUpperRightY() - (50 + BORDER_TOP);

		contentStream.moveTo(floatingX, upperY);
		contentStream.lineTo(floatingX, lowerY);
		contentStream.stroke();

		for (int colindex = 0; colindex < COL_SIZE.length; colindex++) {

			floatingX += COL_SIZE[colindex];
			contentStream.moveTo(floatingX, upperY);
			contentStream.lineTo(floatingX, lowerY);

			contentStream.stroke();
		}
	}

	private void addGenderSign(int i) throws IOException {

		PDImageXObject pdImage = null;
		if (getModel().getContacts().get(i).getGender() == "m") {
			final byte[] image = loadImageByte("/male.jpg");
			ByteArrayInputStream bais = new ByteArrayInputStream(image);
			BufferedImage bim = ImageIO.read(bais);
			pdImage = LosslessFactory.createFromImage(document, bim);

		} else {

			final byte[] image = loadImageByte("/female.jpg");
			ByteArrayInputStream bais = new ByteArrayInputStream(image);
			BufferedImage bim = ImageIO.read(bais);
			pdImage = LosslessFactory.createFromImage(document, bim);

		}

		contentStream.drawImage(pdImage, PAGESIZE.getUpperRightX() - BORDER_RIGHT - 30,
				PAGESIZE.getUpperRightY() - BORDER_TOP - 68 - (ROWHEIGHT * i), 16, 16);
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

	private byte[] loadImageByte(String imageFilename) {
		byte[] dataBytes = null;
		try {
			InputStream is = getClass().getResourceAsStream(imageFilename);
			dataBytes = new byte[is.available()];
			is.read(dataBytes);
		} catch (IOException ex) {
			Logger.getLogger(Szenario3ApacheImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return dataBytes;
	}

}
