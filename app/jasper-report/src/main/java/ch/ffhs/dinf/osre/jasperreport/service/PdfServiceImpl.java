package ch.ffhs.dinf.osre.jasperreport.service;

import org.springframework.stereotype.Component;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;
import ch.ffhs.dinf.osre.itext.service.Szenario1Impl;
import ch.ffhs.dinf.osre.itext.service.Szenario2Impl;
import ch.ffhs.dinf.osre.itext.service.Szenario3Impl;

@Component
public class PdfServiceImpl implements PdfEngine {

	private static final String NAME = "Jasper Reports";

	@Override
	public PdfResponse createPdfSzenario1(PdfRequestScenario1 model) {
		Szenario1Impl szenario1Impl = new Szenario1Impl(NAME, "Szenario 1");
		szenario1Impl.setModel(model);
		return szenario1Impl.build();
	}

	@Override
	public PdfResponse createPdfSzenario2(PdfRequestScenario2 model) {
		Szenario2Impl szenario2Impl = new Szenario2Impl(NAME, "Szenario 2");
		szenario2Impl.setModel(model);
		return szenario2Impl.build();
	}

	@Override
	public PdfResponse createPdfSzenario3(PdfRequestScenario3 model) {
		Szenario3Impl szenario3Impl = new Szenario3Impl(NAME, "Szenario 3");
		szenario3Impl.setModel(model);
		return szenario3Impl.build();
	}

}
