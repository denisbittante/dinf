package jmxTimer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchedulerChanger {

	private double startTime = 1516975200000d;
	/// private float startTime = 9999999999f;
	private double runningTime = 10d * 60d * 1000d;
	private double breakTime = 10d * 60d * 1000d;

	public String findAndReplaceSchedule(String program) {

		String replaceStart = replaceStart(program);
		return replaceEnd(replaceStart);
	}

	private String replaceEnd(String program) {
		Pattern patt = Pattern.compile("(<longProp name=\"ThreadGroup.end_time\">)(\\d+)(</longProp>)");
		Matcher m = patt.matcher(program);
		StringBuffer sb = new StringBuffer(program.length());

		int i = 0;

		while (m.find()) {
			String textstart = m.group(1);
			String textend = m.group(3);

			double time = startTime + (i * (runningTime + breakTime)) + runningTime;

			String string = textstart + String.format("%d", (long) time) + textend;
			m.appendReplacement(sb, string);

			i++;
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private String replaceStart(String program) {
		Pattern patt = Pattern.compile("(<longProp name=\"ThreadGroup.start_time\">)(\\d+)(</longProp>)");
		Matcher m = patt.matcher(program);
		StringBuffer sb = new StringBuffer(program.length());

		int i = 0;
		while (m.find()) {
			String textstart = m.group(1);
			String textend = m.group(3);

			double time = startTime + (i * (runningTime + breakTime));

			String string = textstart + String.format("%d", (long) time) + textend;
			m.appendReplacement(sb, string);
			i++;
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
