package ch.ffhs.dinf.osre.engine;

import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import ch.ffhs.dinf.osre.engine.api.PdfResponse;

public interface PdfEngine {

	PdfResponse createPdfSzenario1(PdfRequestScenario1 model);

	PdfResponse createPdfSzenario2(PdfRequestScenario2 model);

	PdfResponse createPdfSzenario3(PdfRequestScenario3 model);

}