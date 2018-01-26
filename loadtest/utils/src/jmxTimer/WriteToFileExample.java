package jmxTimer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample {

	private static final String FILENAME = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\testingTime-CHANGED.jmx";

	public static void writeFile(String content) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {

			bw.write(content);
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}