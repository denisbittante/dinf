package ch.ffhs.dinf.osre.itext.service;

import org.springframework.stereotype.Component;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@Component
public class PdfServiceImpl implements PdfEngine {

	private static final String NAME = "iText";

	@Override
	public PdfResponse createPdfSzenario1(PdfRequestScenario1 model) {
		return new PdfResponse(NAME, "Szenario 1");
	}

	@Override
	public PdfResponse createPdfSzenario2(PdfRequestScenario2 model) {
		return new PdfResponse(NAME, "Szenario 2");
	}

	@Override
	public PdfResponse createPdfSzenario3(PdfRequestScenario3 model) {
		return new PdfResponse(NAME, "Szenario 3");
	}

}
