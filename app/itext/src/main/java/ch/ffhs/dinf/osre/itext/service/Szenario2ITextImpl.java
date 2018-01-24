package ch.ffhs.dinf.osre.itext.service;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario2;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.itext.service.Utils.Style;

public class Szenario2ITextImpl extends AbstractScenario<PdfRequestScenario2> implements Scenario2 {

	private PdfDocument pdfDoc;

	public Szenario2ITextImpl(String name, String description) {
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

	private void createChapters(Document document) {

		Utils utils = new Utils();

		Color mainColor = new DeviceRgb(47, 72, 110); // blue 
		Color subColor = new DeviceRgb(196, 134, 39); // gold

		for (ActivityGroup group : getModel().getGroup()) {

			Table element = new Table(3);
			element.setWidth(UnitValue.createPercentValue(100f));
			element.setBorder(Border.NO_BORDER);

			Cell cell = new Cell(0, 3);
			Paragraph titleParagraph = (Paragraph) utils.style(new Paragraph(group.getTitle()), Style.H1);
			cell.add(titleParagraph);
			cell.setBorder(Border.NO_BORDER);
			cell.setBackgroundColor(mainColor);
			cell.setFontColor(ColorConstants.WHITE);
			element.addCell(cell);
			for (ActivityEntry entry : group.getEntries()) {

				if (entry.isSubtitle()) {
					Cell cellsub = new Cell(0, 3);
					Paragraph subtitleParagraph = (Paragraph) utils.style(new Paragraph(entry.getTitle()), Style.H3);
					cellsub.add(subtitleParagraph);
					cellsub.setBackgroundColor(subColor);
					cellsub.setBorder(Border.NO_BORDER);
					cellsub.setFontColor(ColorConstants.WHITE);
					element.addCell(cellsub);

				} else {

					Paragraph timePar = (Paragraph) utils.style(new Paragraph(entry.getTime()), Style.bold_italic);
					Cell timecell = new Cell();
					timecell.setBorder(Border.NO_BORDER);
					timecell.add(timePar);
					timecell.setWidth(UnitValue.createPercentValue(10f));

					Paragraph titlePar = new Paragraph(entry.getTitle());
					Cell titlecell = new Cell();
					titlecell.setBorder(Border.NO_BORDER);
					titlecell.add(titlePar);
					titlecell.setWidth(UnitValue.createPercentValue(70f));
					Paragraph personPar = (Paragraph) utils.style(new Paragraph(entry.getPerson()), Style.italic);
					Cell personcell = new Cell();
					personcell.add(personPar);
					personcell.setBorder(Border.NO_BORDER);
					personcell.setWidth(UnitValue.createPercentValue(20f));

					element.addCell(timecell);
					element.addCell(titlecell);
					element.addCell(personcell);
				}
			}

			Cell cellfooter = new Cell(0, 3);
			Paragraph footerParagraph = (Paragraph) utils.style(new Paragraph(group.getFooter()), Style.small);

			cellfooter.add(footerParagraph);
			cellfooter.setBorder(Border.NO_BORDER);

			element.addCell(cellfooter);
			document.add(element);

		}

	}

	private void setMetadata() {
		setAuthor();
		setKeywords();
		setSubject();
		setTitle();
	}
}
