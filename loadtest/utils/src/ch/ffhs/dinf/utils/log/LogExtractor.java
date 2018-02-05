package ch.ffhs.dinf.utils.log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ffhs.dinf.utils.FileUtils;

public class LogExtractor {

	private static final String readFile = "C:\\Users\\Denis Bittante\\Downloads\\2018-02-02.tsv\\2018-02-02.tsv";
	private static final String writeFile = "C:\\sandbox\\dinf\\dinf\\loadtest\\results\\heroku-log\\memory.csv";

	public static void main(String[] args) {

		String readEachline = readEachline();
		FileUtils.writeFile(readEachline.toString(), writeFile);

	}

	public static String readEachline() {

		Pattern patt = Pattern.compile(
				// "(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{6}\\+\\d{2}:\\d{2})
				// heroku\\[web.1\\]: source=web.1 dyno=([\\w|\\.|-]){52}
				// sample#memory_total=(\\d+\\.\\d{2})MB
				// sample#memory_rss=(\\d+\\.\\d{2})MB
				// sample#memory_cache=(\\d+\\.\\d{2})MB
				// sample#memory_swap=(\\d+\\.\\d{2})MB
				// sample#memory_pgpgin=(\\d+)pages
				// sample#memory_pgpgout=(\\d+)pages
				// sample#memory_quota=(\\d+\\.\\d{2})MB");
				"\\d+\\s[\\d|\\-\\:|\\w]+\\s[\\d|\\-\\:|\\w]+\\s(\\d+)\\s[\\w|\\-]+\\s[\\d|\\.]+\\s\\w+\\s\\w+\\s[\\w|/|\\.]+\\ssource=web.1 dyno=[\\w|\\.|-]{52} sample#memory_total=(\\d+\\.\\d{2})MB sample#memory_rss=(\\d+\\.\\d{2})MB sample#memory_cache=(\\d+\\.\\d{2})MB sample#memory_swap=(\\d+\\.\\d{2})MB sample#memory_pgpgin=(\\d+)pages sample#memory_pgpgout=(\\d+)pages sample#memory_quota=(\\d+\\.\\d{2})MB");

		StringBuffer buffer = new StringBuffer();

		buffer.append("timestamp,");
		buffer.append("memory_total (MB),");
		buffer.append("memory_rss (MB),");
		buffer.append("memory_cache(MB),");
		buffer.append("memory_swap(MB),");
		buffer.append("memory_pgpgin (pages),");
		buffer.append("memory_pgpgout (pages),");
		buffer.append("memory_quota (MB)\n");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				Matcher m = patt.matcher(sCurrentLine);

				while (m.find()) {

					buffer.append(getLoadTest(m.group(1)));
					buffer.append(",");
					buffer.append(m.group(1));
					buffer.append(",");
					buffer.append(m.group(2));
					buffer.append(",");
					buffer.append(m.group(3));
					buffer.append(",");
					buffer.append(m.group(4));
					buffer.append(",");
					buffer.append(m.group(5));
					buffer.append(",");
					buffer.append(m.group(6));
					buffer.append(",");
					buffer.append(m.group(7));
					buffer.append(",");
					buffer.append(m.group(8));
					buffer.append("\n");

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
			"PdfBox-1b", "PdfBox-1c", "PdfBox-2a", "PdfBox-2b", "PdfBox-2c", "PdfBox-3a", "PdfBox-3b", "PdfBox-3c", };

	private static String getLoadTest(String group) {

		double time = Double.valueOf(group);
		double start = 1517580000000d;
		double timetask = 20d * 60d * 1000d;

		String process = "";

		double d = time - start;
		double e = d / timetask;

		if (e < 30 && e > 0) {
			return stages[(int) e];
		}
		return "none";

	}

}
