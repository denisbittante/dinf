package ch.ffhs.dinf.osre.engine;

import ch.ffhs.dinf.osre.engine.api.PdfRequest;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;


public interface PdfEngine {

	PdfResponse createPdfSzenario1(PdfRequest model);

	PdfResponse createPdfSzenario2(PdfRequest model);

	PdfResponse createPdfSzenario3(PdfRequest model);

}