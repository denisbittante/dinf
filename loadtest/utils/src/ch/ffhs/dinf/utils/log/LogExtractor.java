package ch.ffhs.dinf.utils.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ffhs.dinf.utils.FileUtils;

public class LogExtractor {

	private static final String readFolder = "C:\\sandbox\\dinf\\dinf\\loadtest\\results\\heroku-log\\";
	private static final String writeFile = "C:\\sandbox\\dinf\\dinf\\loadtest\\results\\heroku-log\\";

	private static final Pattern pattRessources = Pattern.compile(
			"\\d+\\s([\\d|\\-\\:|\\w]+)\\s[\\d|\\-\\:|\\w]+\\s\\d+\\s[\\w|\\-]+\\s[\\d|\\.]+\\s\\w+\\s\\w+\\s[\\w|/|\\.]+\\ssource=web.1 dyno=[\\w|\\.|-]{52} sample#memory_total=(\\d+\\.\\d{2})MB sample#memory_rss=(\\d+\\.\\d{2})MB sample#memory_cache=(\\d+\\.\\d{2})MB sample#memory_swap=(\\d+\\.\\d{2})MB sample#memory_pgpgin=(\\d+)pages sample#memory_pgpgout=(\\d+)pages sample#memory_quota=(\\d+\\.\\d{2})MB");

	private static final Pattern loadPattern = Pattern.compile(
			"\\d+\\s([\\d|\\-\\:|\\w]+)\\s[\\d|\\-\\:|\\w]+\\s(\\d+)\\s[\\w|\\-]+\\s[\\d|\\.]+\\s\\w+\\s\\w+\\s[\\w|/|\\.]+\\ssource=web.1 dyno=[\\w|\\.|-]{52} sample#load_avg_1m=(\\d+\\.\\d{2}) sample#load_avg_5m=(\\d+\\.\\d{2}) sample#load_avg_15m=(\\d+\\.\\d{2})");

	public static void main(String[] args) {

		parseFiles(getLoadHeader(), "load.csv", loadPattern);

		// parseFiles(getMemoryHeader(), "memory.csv", pattRessources);

	}

	private static void parseFiles(String header, String filename, Pattern pattern) {
		StringBuffer readEachline = new StringBuffer();

		readEachline.append(header);

		File folder = new File(readFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.getName().contains(".tsv")) {
				readEachline.append(readEachline(file.getAbsolutePath(), pattern));
			}
		}
		FileUtils.writeFile(readEachline.toString(), writeFile + filename);
	}

	private static String getLoadHeader() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("label,product,szenario");
		buffer.append("date,");
		buffer.append("load_avg_1m,");
		buffer.append("load_avg_5m,");
		buffer.append("load_avg_15m\n");
		return buffer.toString();
	}

	private static String getMemoryHeader() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("label,product,szenario");
		buffer.append("date,");
		buffer.append("memory_total (MB),");
		buffer.append("memory_rss (MB),");
		buffer.append("memory_cache(MB),");
		buffer.append("memory_swap(MB),");
		buffer.append("memory_pgpgin (pages),");
		buffer.append("memory_pgpgout (pages),");
		buffer.append("memory_quota (MB)\n");
		return buffer.toString();
	}

	public static String readEachline(String readFile, Pattern pattern) {

		StringBuffer buffer = new StringBuffer();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				Matcher m = pattern.matcher(sCurrentLine);

				while (m.find()) {
					String loadTest = getLoadTest(m.group(1));

					if (!loadTest.equals("none")) {

						buffer.append(loadTest);
						buffer.append(",");

						for (int i = 1; i <= m.groupCount(); i++) {
							buffer.append(m.group(i));
							buffer.append(",");

						}
						buffer.append("\n");

					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();

	}

	private static String[] stages = { "iText-deploy", "iText-1a", "iText-1b", "iText-1c", "iText-2a", "iText-2b",
			"iText-2c", "iText-3a", "iText-3b", "iText-3c", "Jasper-deploy", "Jasper-1a", "Jasper-1b", "Jasper-1c",
			"Jasper-2a", "Jasper-2b", "Jasper-2c", "Jasper-3a", "Jasper-3b", "Jasper-3c", "PdfBox-deploy", "PdfBox-1a",
			"PdfBox-1b", "PdfBox-1c", "PdfBox-2a", "PdfBox-2b", "PdfBox-2c", "PdfBox-3a", "PdfBox-3b", "PdfBox-3c" };

	private static String[] szenario = { "deploy", "1a", "1b", "1c", "2a", "2b", "2c", "3a", "3b", "3c", "deploy", "1a",
			"1b", "1c", "2a", "2b", "2c", "3a", "3b", "3c", "deploy", "1a", "1b", "1c", "2a", "2b", "2c", "3a", "3b",
			"3c" };

	private static String[] product = { "iText", "iText", "iText", "iText", "iText", "iText", "iText", "iText", "iText",
			"iText", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper", "Jasper",
			"PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox", "PdfBox" };

	protected static String getLoadTest(String date) {

		// 2018-02-06T05:59:59
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date dateStr;
		try {
			dateStr = formatter.parse(date);
		} catch (ParseException e1) {
			return "none";
		}

		double time = Double.valueOf(dateStr.getTime());
		double start = 1517961600000d - (60d * 60d * 1000d);
		double timetask = 20d * 60d * 1000d;

		double d = time - start;
		double e = d / timetask;

		if (e < 30 && e > 0) {
			return stages[(int) e] + "," + product[(int) e] + "," + szenario[(int) e] + ", " + Double.toString(time);
		}
		return "none";

	}

}
