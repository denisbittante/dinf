package ch.ffhs.dinf.osre.apachepdf.service;

import org.springframework.stereotype.Component;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@Component
public class PdfServiceApacheImpl implements PdfEngine {

	final static String NAME = "ApachePdfBox";

	@Override
	public PdfResponse createPdfSzenario1(PdfRequestScenario1 model) {

		Szenario1ApacheImpl szenario1Impl = new Szenario1ApacheImpl(NAME, "Szenario 1");
		szenario1Impl.setModel(model);
		return szenario1Impl.build();
	}

	@Override
	public PdfResponse createPdfSzenario2(PdfRequestScenario2 model) {
		Szenario2ApacheImpl szenario2Impl = new Szenario2ApacheImpl(NAME, "Szenario 2");
		szenario2Impl.setModel(model);
		return szenario2Impl.build();
	}

	@Override
	public PdfResponse createPdfSzenario3(PdfRequestScenario3 model) {
		Szenario3ApacheImpl szenario3Impl = new Szenario3ApacheImpl(NAME, "Szenario 3");
		szenario3Impl.setModel(model);
		return szenario3Impl.build();

	}

}
