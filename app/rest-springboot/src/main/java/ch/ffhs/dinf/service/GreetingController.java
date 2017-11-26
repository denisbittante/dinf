package ch.ffhs.dinf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.Pdf;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;

@RestController
public class GreetingController {

	@Autowired
	private PdfEngine engine;

	@RequestMapping(method = RequestMethod.GET, path = "/scenario1")
	public Pdf scenario1(PdfRequest req) {

		return engine.createPdfSzenario1(req);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/scenario2")
	public Pdf scenario2(PdfRequest req) {

		return engine.createPdfSzenario2(req);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/scenario3")
	public Pdf scenario3(PdfRequest req) {

		return engine.createPdfSzenario3(req);

	}
}