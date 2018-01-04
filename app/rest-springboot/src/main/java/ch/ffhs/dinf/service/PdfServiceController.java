package ch.ffhs.dinf.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.ffhs.dinf.osre.engine.PdfEngine;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

@RestController("/")
public class PdfServiceController {

	@Autowired
	private PdfEngine engine;

	@RequestMapping(method = RequestMethod.POST, path = "scenario1")
	public PdfResponse scenario1(PdfRequest req) {

		PdfRequest pdfRequest = new PdfRequest();
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("test2", "test322");
		HashMap<String, String> hashMap2 = new HashMap<>();
		hashMap2.put("test2", "test322");
		data.add(hashMap);
		data.add(hashMap2);

		pdfRequest.setData(data);

		return engine.createPdfSzenario1(pdfRequest);
	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario2")
	public PdfResponse scenario2(PdfRequest req) {

		return engine.createPdfSzenario2(req);

	}

	@RequestMapping(method = RequestMethod.POST, path = "scenario3")
	public PdfResponse scenario3(PdfRequest req) {

		return engine.createPdfSzenario3(req);

	}
}