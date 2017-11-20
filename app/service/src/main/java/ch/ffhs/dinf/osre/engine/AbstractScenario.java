package ch.ffhs.dinf.osre.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

public abstract class AbstractScenario {

	private final File tempfile;

	/**
	 * Creates a temp File to save it during the process of creating a Pdf.
	 */
	public AbstractScenario() {

		UUID randomUUID = UUID.randomUUID();
		tempfile = new File(randomUUID.toString());
	}

	public String toBase64() {

		String encodedBase64 = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(tempfile);
			byte[] bytes = new byte[(int) tempfile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedBase64;

	}

	/**
	 * Release Ressouces etc.
	 */
	public void finalizeScenario() {
		tempfile.deleteOnExit();

	}

}
