package ch.ffhs.dinf.osre.itext.service;

import java.io.IOException;

import org.junit.Test;

import ch.ffhs.dinf.osre.engine.api.PdfResponse;
import ch.ffhs.dinf.osre.engine.test.TestUtils;

public class PdfServiceImplTest {

	PdfServiceImpl testee = new PdfServiceImpl();

	
	@Test
	public void testScenario1() throws IOException {

		PdfResponse pdfResponse = testee.createPdfSzenario1(TestUtils.getPdfRequest1());
		TestUtils.showPdf(pdfResponse);

	}

	
	@Test
	public void testScenario2() throws IOException {
		PdfResponse pdfResponse = testee.createPdfSzenario2(TestUtils.getPdfRequest2());
		TestUtils.showPdf(pdfResponse);

	}

	@Test
	public void testScenario3() throws IOException {

		PdfResponse pdfResponse = testee.createPdfSzenario3(TestUtils.getPdfRequest3());
		TestUtils.showPdf(pdfResponse);

	}

}
