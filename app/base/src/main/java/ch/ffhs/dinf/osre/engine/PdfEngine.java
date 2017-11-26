package ch.ffhs.dinf.osre.engine;

import ch.ffhs.dinf.osre.engine.api.Pdf;
import ch.ffhs.dinf.osre.engine.api.PdfRequest;


public interface PdfEngine {

	Pdf createPdfSzenario1(PdfRequest model);

	Pdf createPdfSzenario2(PdfRequest model);

	Pdf createPdfSzenario3(PdfRequest model);

}