package ch.ffhs.dinf.utils.jmx.time;

import java.util.GregorianCalendar;

import ch.ffhs.dinf.utils.FileUtils;

public class Main {

	private static final String outputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\LoadTest-TIMED.jmx";

	private static final String outputBackupFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\backup\\LoadTest-Backup"
			+ new GregorianCalendar().getTimeInMillis() + ".jmx";

	private static final String inputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\DinfLoadTest.jmx";

	private static final GregorianCalendar startTimeStamp = new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 9,
			01, 00, 00);
	private static final double runningTime = 10d * 60d * 1000d;
	private static final double breakTime = 10d * 60d * 1000d;

	public static void main(String[] args) {

		String readFile = FileUtils.readFile(inputFilename);
		SchedulerChanger changer = new SchedulerChanger(runningTime, breakTime);
		String findAndReplaceSchedule = changer.findAndReplaceSchedule(readFile, startTimeStamp);
		// System.out.println(findAndReplaceSchedule);

		FileUtils.writeFile(findAndReplaceSchedule, outputFilename);
		FileUtils.writeFile(findAndReplaceSchedule, outputBackupFilename);

	}
}
