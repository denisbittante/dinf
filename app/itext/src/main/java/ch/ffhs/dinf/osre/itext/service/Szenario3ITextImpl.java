package ch.ffhs.dinf.osre.itext.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario3;
import ch.ffhs.dinf.osre.engine.api.Contact;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.itext.service.Utils.Style;

public class Szenario3ITextImpl extends AbstractScenario<PdfRequestScenario3> implements Scenario3 {

	private PdfDocument pdfDoc;

	public Szenario3ITextImpl(String name, String description) {
		super(name, description);
	}

	@Override
	public void setAuthor() {
		pdfDoc.getDocumentInfo().setAuthor(AUTHOR);
	}

	@Override
	public void setFont(FontType t) throws Exception {
		// not needed style-Method in Utils.
	}

	@Override
	public void createChapters() throws Exception {
		// implemented in createChapters;
	}

	@Override
	public void setSubject() {
		pdfDoc.getDocumentInfo().setSubject(SUBJECT);

	}

	@Override
	public void setKeywords() {
		pdfDoc.getDocumentInfo().setKeywords(KEYWORDS);
	}

	@Override
	public void setTitle() {
		pdfDoc.getDocumentInfo().setTitle(SUBJECT);

	}

	@Override
	protected void buildPdf() throws Exception {

		// Creating a PdfWriter
		PdfWriter writer = new PdfWriter(getTempfile().getAbsolutePath());

		// Creating a PdfDocument
		pdfDoc = new PdfDocument(writer);
		pdfDoc.setDefaultPageSize(new PageSize(PageSize.A4.getHeight(), PageSize.A4.getWidth()));

		setMetadata();

		Document document = new Document(pdfDoc);
		document.setFont(Style.small.getFont());
		document.setFontSize(Style.small.fontsize);
		createChapters(document);
		// Closing the document
		document.close();

		//System.out.println("PDF Created");

	}

	private void createChapters(Document document) throws MalformedURLException {

		document.add((Paragraph) (new Utils()).style(new Paragraph("Kontaktliste"), Style.H1));

		Table table = new Table(11);
		table.setWidth(UnitValue.createPercentValue(100f));

		table.addHeaderCell("Name");
		table.addHeaderCell("Vorname");
		table.addHeaderCell("Strasse");
		table.addHeaderCell("PLZ");
		table.addHeaderCell("Ort");
		table.addHeaderCell("Tel.");
		table.addHeaderCell("Mob.");
		table.addHeaderCell("Geburtsdatum");
		table.addHeaderCell("Bemerkung");
		table.addHeaderCell("E-Mail");
		table.addHeaderCell("Geschlecht");
		table.getHeader().setFont(Style.bold.getFont());
		table.getHeader().setFontSize(Style.small.fontsize);
		for (Contact group : getModel().getContacts()) {

			Cell cell1 = new Cell();
			cell1.add(new Paragraph(group.getName()));
			Cell cell2 = new Cell();
			cell2.add(new Paragraph(group.getFirstname()));
			Cell cell3 = new Cell();
			cell3.add(new Paragraph(group.getAddress()));
			Cell cell4 = new Cell();
			cell4.add(new Paragraph(group.getZip()));
			Cell cell5 = new Cell();
			cell5.add(new Paragraph(group.getPlace()));
			Cell cell6 = new Cell();
			cell6.add(new Paragraph(group.getTel()));
			Cell cell7 = new Cell();
			cell7.add(new Paragraph(group.getMob()));
			Cell cell8 = new Cell();
			cell8.add(new Paragraph(group.getBirthday()));
			Cell cell9 = new Cell();
			cell9.add(new Paragraph(group.getNote()));
			Cell cell10 = new Cell();
			cell10.add(new Paragraph(group.getEmail()));

			Cell cell11 = new Cell();
			PdfImageXObject create = null;
			if (group.getGender() == "m") {

				ImageData imgData = ImageDataFactory.create(loadImageByte("/male.jpg"), true);
				/* Wrapping image data in a PdfImageXObject */
				create = new PdfImageXObject(imgData);

				// create =
				// ImageDataFactory.create(getClass().getResource("/male.jpg").getPath());

			} else {

				ImageData imgData = ImageDataFactory.create(loadImageByte("/female.jpg"), true);
				/* Wrapping image data in a PdfImageXObject */
				create = new PdfImageXObject(imgData);

			}
			Image element = new Image(create);
			element.scaleToFit(20, 20);
			cell11.add(element);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);

		}

		document.add(table);

	}

	private byte[] loadImageByte(String imageFilename) {
		byte[] dataBytes = null;
		try {
			InputStream is = getClass().getResourceAsStream(imageFilename);
			dataBytes = new byte[is.available()];
			is.read(dataBytes);
		} catch (IOException ex) {
			Logger.getLogger(Szenario3ITextImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return dataBytes;
	}

	private void setMetadata() {
		setAuthor();
		setKeywords();
		setSubject();
		setTitle();
	}

}
