package ch.ffhs.dinf.osre.engine;

import java.io.File;

import ch.ffhs.dinf.osre.api.model.Pdf;

public interface PdfEngine {

	File createPdfSzenario1(Pdf model);

	File createPdfSzenario2(Pdf model);

	File createPdfSzenario3(Pdf model);

}
