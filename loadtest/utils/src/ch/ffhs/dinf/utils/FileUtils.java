package ch.ffhs.dinf.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static String readFile(String filename) {

		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				buffer.append(sCurrentLine + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();

	}

	public static void writeFile(String content, String filename) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

			bw.write(content);
			// System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}