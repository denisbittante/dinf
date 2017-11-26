package ch.ffhs.dinf.osre.jasperreport.service;

import org.springframework.stereotype.Component;
import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.Pdf;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;

@Component
public class PdfServiceImpl implements PdfEngine {

	private static final String NAME = "Jasper Reports";

	@Override
	public ch.ffhs.dinf.osre.engine.api.Pdf createPdfSzenario1(PdfRequest model) {
		return new Pdf(NAME, "Szenario 1");
	}

	@Override
	public ch.ffhs.dinf.osre.engine.api.Pdf createPdfSzenario2(PdfRequest model) {
		return new Pdf(NAME, "Szenario 2");
	}

	@Override
	public ch.ffhs.dinf.osre.engine.api.Pdf createPdfSzenario3(PdfRequest model) {
		return new Pdf(NAME, "Szenario 3");
	}

}
