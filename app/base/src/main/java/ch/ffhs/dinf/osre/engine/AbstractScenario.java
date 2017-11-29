package ch.ffhs.dinf.osre.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import ch.ffhs.dinf.osre.engine.api.Pdf;

public abstract class AbstractScenario {

	private final File tempfile;

	public File getTempfile() {
		return tempfile;
	}

	private Pdf pdf;

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

		pdf = new Pdf(name, description);

		UUID randomUUID = UUID.randomUUID();
		tempfile = new File("C:\\temp\\" + randomUUID.toString() + ".pdf");

	}

	public String toBase64() throws Exception {

		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = new FileInputStream(tempfile);
		byte[] bytes = new byte[(int) tempfile.length()];
		fileInputStreamReader.read(bytes);
		return new String(Base64.encodeBase64(bytes));

	}

	/**
	 * Release Ressouces etc.
	 */
	public void finalizeScenario() {
		tempfile.deleteOnExit();
	}

	/**
	 * to be implemented for each Scenario
	 * 
	 * @throws Exception
	 */
	protected abstract void buildPdf() throws Exception;

	public Pdf build() {
		try {
			createFile();
			buildPdf();
			this.pdf.setFile(toBase64());
			this.pdf.setStatus("ok");
		} catch (Exception e) {
			this.pdf.setStatus("nok");
			System.err.print(e);
		}
		return this.pdf;

	}

	private void createFile() {
		try {
			tempfile.createNewFile();
			
		} catch (IOException e) {

		}
	}

}
