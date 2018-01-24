package ch.ffhs.dinf.osre.itext.service;

import java.util.Date;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario1;
import ch.ffhs.dinf.osre.engine.api.ActivityDetails;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.itext.service.Utils.Style;

public class Szenario1ITextImpl extends AbstractScenario<PdfRequestScenario1> implements Scenario1 {

	private PdfDocument pdfDoc;

	public Szenario1ITextImpl(String name, String description) {
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

		setMetadata();

		Document document = new Document(pdfDoc);
		document.setFont(Style.normal.getFont());
		document.setFontSize(Style.normal.fontsize);
		createChapters(document);
		// Closing the document
		document.close();

		System.out.println("PDF Created");

	}

	private void setMetadata() {
		setAuthor();
		setKeywords();
		setSubject();
		setTitle();
	}

	private void createChapters(Document document) {
		// Adding a new page
		pdfDoc.addNewPage();

		for (int i = 0; i < getModel().getData().size(); i++) {
			ActivityDetails aD = getModel().getData().get(i);

			if (i > 0) {
				document.add(new AreaBreak());
			}

			// Creating a Document

			pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(document, aD));
			// Creating Paragraphs

			Utils utils = new Utils();

			document.add((Paragraph) utils.style(new Paragraph(aD.getTitle()), Style.H1));
			document.add((Paragraph) utils.style(new Paragraph(aD.getDatumVon() + " - " + aD.getDatumBis()), Style.H2));
			document.add(new Paragraph(aD.getDescription()));
			document.add((Paragraph) utils.style(new Paragraph("Ort"), Style.bold));
			document.add(new Paragraph(aD.getPlace()));
			document.add((Paragraph) utils.style(new Paragraph("Verantwortlicher"), Style.bold));
			document.add(new Paragraph(aD.getIncharge()));
			document.add((Paragraph) utils.style(new Paragraph("Helfer"), Style.bold));
			document.add(new Paragraph(aD.getHelper()));

		}
	}

	protected class TextFooterEventHandler implements IEventHandler {
		protected Document doc;
		protected ActivityDetails aD;

		public TextFooterEventHandler(Document doc, ActivityDetails aD) {
			this.doc = doc;
			this.aD = aD;
		}

		@Override
		public void handleEvent(Event event) {
			PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
			PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
			Rectangle pageSize = docEvent.getPage().getPageSize();
			canvas.beginText();
			try {
				canvas.setFontAndSize(Style.small.getFont(), Style.small.fontsize);
			} catch (IOException e) {
				e.printStackTrace();
			}

			//@formatter:off
			float y  = (pageSize.getBottom() + doc.getBottomMargin())- (pageSize.getTop() + doc.getTopMargin()) + 40;
			System.out.println( "y : "+ y);
			//y =-800f;
			float halfpage = (pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2 ;
			canvas.moveText(halfpage, pageSize.getTop() - doc.getTopMargin() + 10)
					.moveText(0,  y)
					.showText(docEvent.getDocument().getPageNumber(docEvent.getPage()) + " / " + docEvent.getDocument().getNumberOfPages() )
					.moveText(- halfpage + doc.getLeftMargin(),  0)
					.showText("Erstellt von: "+ aD.getAuthor())
					.moveText( halfpage * 2 - doc.getRightMargin() - 80 ,  0)
					.showText(new Date().toString())
					.endText()
					.release();
			//@formatter:on

		}
	}

}
