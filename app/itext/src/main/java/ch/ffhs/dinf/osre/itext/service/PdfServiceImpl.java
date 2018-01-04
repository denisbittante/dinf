package ch.ffhs.dinf.osre.itext.service;

import org.springframework.stereotype.Component;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@Component
public class PdfServiceImpl implements PdfEngine {

	private static final String NAME = "iText";

	@Override
	public PdfResponse createPdfSzenario1(PdfRequest model) {
		return new PdfResponse(NAME, "Szenario 1");
	}

	@Override
	public PdfResponse createPdfSzenario2(PdfRequest model) {
		return new PdfResponse(NAME, "Szenario 2");
	}

	@Override
	public PdfResponse createPdfSzenario3(PdfRequest model) {
		return new PdfResponse(NAME, "Szenario 3");
	}

}
