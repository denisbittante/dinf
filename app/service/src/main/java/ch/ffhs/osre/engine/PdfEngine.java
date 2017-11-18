package ch.ffhs.osre.engine;

import java.io.File;

import io.swagger.model.Pdf;

public interface PdfEngine {

	File createPdfSzenario1(Pdf model);

	File createPdfSzenario2(Pdf model);

	File createPdfSzenario3(Pdf model);

}
