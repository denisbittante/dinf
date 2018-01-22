package ch.ffhs.dinf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody PdfResponse scenario1(@RequestBody PdfRequestScenario1 req) {

		return engine.createPdfSzenario1(req);

	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario2")
	public @ResponseBody PdfResponse scenario2(@RequestBody PdfRequestScenario2 req) {

		return engine.createPdfSzenario2(req);
	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario3")
	public @ResponseBody PdfResponse scenario3(@RequestBody PdfRequestScenario3 req) {

		return engine.createPdfSzenario3(req);

	}
}