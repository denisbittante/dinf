package jmxTimer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample {

	private static final String FILENAME = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\testingTime.jmx";

	public String readFile() {

		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				buffer.append(sCurrentLine+ "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();

	}

}