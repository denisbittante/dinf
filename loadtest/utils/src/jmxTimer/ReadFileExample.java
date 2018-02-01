package jmxTimer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExample {


	public String readFile(String filename) {

		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

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