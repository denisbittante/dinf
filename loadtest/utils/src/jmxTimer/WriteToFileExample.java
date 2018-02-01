package jmxTimer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample {


	public static void writeFile(String content, String filename) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

			bw.write(content);
		//	System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}