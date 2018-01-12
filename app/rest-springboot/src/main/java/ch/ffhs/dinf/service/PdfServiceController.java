package ch.ffhs.dinf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@RestController("/")
public class PdfServiceController {

	@Autowired
	private PdfEngine engine;

	@RequestMapping(method = RequestMethod.POST, path = "scenario1")
	public PdfResponse scenario1(PdfRequestScenario1 req) {

		return engine.createPdfSzenario1(req);

	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario2")
	public PdfResponse scenario2(PdfRequestScenario2 req) {

		return engine.createPdfSzenario2(req);

	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario3")
	public PdfResponse scenario3(PdfRequestScenario3 req) {

		return engine.createPdfSzenario3(req);

	}
}