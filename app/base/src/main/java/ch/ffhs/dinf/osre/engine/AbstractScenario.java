package ch.ffhs.dinf.osre.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import ch.ffhs.dinf.osre.engine.api.PdfResponse;

public abstract class AbstractScenario<M> {

	private M model;
	private final String name;

	private PdfResponse pdf;

	private final File tempfile;

	/**
	 * Creates a temp File to save it during the process of creating a Pdf.
	 * 
	 * @param string
	 *            Description of scenario
	 * @param name
	 *            Name of OSRE
	 * @throws IOException
	 */
	public AbstractScenario(String name, String description) {

		this.name = name;

		pdf = new PdfResponse(name, description);
		UUID randomUUID = UUID.randomUUID();
		tempfile = new File("C:\\temp\\" + randomUUID.toString() + ".pdf");

	}

	public PdfResponse build() {
		try {
			createFile();
			buildPdf();
			this.pdf.setFile(toBase64());
			this.pdf.setStatus("ok");
		} catch (Exception e) {
			this.pdf.setStatus("nok");
			e.printStackTrace();
		}

		// tryToDeleteTempFile();
		return this.pdf;

	}

	/**
	 * to be implemented for each Scenario
	 * 
	 * @throws Exception
	 */
	protected abstract void buildPdf() throws Exception;

	private void createFile() {
		try {
			tempfile.createNewFile();

		} catch (IOException e) {

		}
	}

	/**
	 * Release Ressouces etc.
	 */
	public void finalizeScenario() {
		tempfile.deleteOnExit();
	}

	public M getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public File getTempfile() {
		return tempfile;
	}

	public void setModel(M model) {
		this.model = model;
	}

	public String toBase64() throws Exception {

		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = new FileInputStream(tempfile);
		byte[] bytes = new byte[(int) tempfile.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(Base64.encodeBase64(bytes));

	}

	private void tryToDeleteTempFile() {

		if (getTempfile().exists()) {
			getTempfile().delete();
			if (!getTempfile().exists()) {
				System.out.println("Deleted succsessfully !");

			}

		}

	}
}
