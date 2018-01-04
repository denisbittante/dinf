package ch.ffhs.dinf.osre.apachepdf.service;

import org.springframework.stereotype.Component;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@Component
public class PdfServiceImpl implements PdfEngine {

	final static String NAME = "ApachePdfBox";

	@Override
	public PdfResponse createPdfSzenario1(PdfRequest model) {

		AbstractScenario szenario1Impl = new Szenario1Impl(NAME, "Szenario 1");
		szenario1Impl.setInput(model.getData());
		return szenario1Impl.build();
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
