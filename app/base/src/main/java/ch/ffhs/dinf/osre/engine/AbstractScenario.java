package ch.ffhs.dinf.osre.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import ch.ffhs.dinf.osre.engine.api.PdfResponse;

public abstract class AbstractScenario {

	private List<HashMap<String, String>> input;
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
			System.err.print(e);
		}

	//	tryToDeleteTempFile();
		return this.pdf;

	}


	/**
	 * to be implemented for each Scenario
	 * 
	 * @throws Exception
	 */
	protected abstract void buildPdf() throws Exception;

	public int countPagesByInput() {

		if (input != null) {
			return input.size();
		} else {
			return 0;
		}
	}

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

	public List<HashMap<String, String>> getInput() {
		return input;
	}

	public File getTempfile() {
		return tempfile;
	}

	protected String input(int i, String string) {
	
		if (getInput().size() >= i) {
	
			if (getInput().get(i).containsKey(string)) {
	
				return getInput().get(i).get(string);
	
			}
		}
	
		return "\" not-found : " + string + "\"";
	}

	public void setInput(List<HashMap<String, String>> input) {
		this.input = input;
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
