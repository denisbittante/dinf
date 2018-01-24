package ch.ffhs.dinf.osre.apachepdf.service;

import java.io.IOException;

import org.junit.Test;

import ch.ffhs.dinf.osre.engine.api.PdfResponse;
import ch.ffhs.dinf.osre.engine.test.TestUtils;

/**
 * 
 * Test Suit for Apache PDF Box
 * 
 * @author Denis Bittante
 *
 */
public class PdfServiceImplTest {

	PdfServiceApacheImpl testee = new PdfServiceApacheImpl();

	@Test
	public void testScenario1() throws IOException {

		PdfResponse createPdfSzenario1 = testee.createPdfSzenario1(TestUtils.getPdfRequest1());
		TestUtils.showPdf(createPdfSzenario1);

	}

	@Test
	public void testScenario2() throws IOException {
		PdfResponse createPdfSzenario1 = testee.createPdfSzenario2(TestUtils.getPdfRequest2());
		TestUtils.showPdf(createPdfSzenario1);

	}

	@Test
	public void testScenario3() throws IOException {

		PdfResponse createPdfSzenario3 = testee.createPdfSzenario3(TestUtils.getPdfRequest3());
		TestUtils.showPdf(createPdfSzenario3);

	}

}
