package ch.ffhs.dinf.utils.jmx.time;

import java.util.Date;
import java.util.GregorianCalendar;

import ch.ffhs.dinf.utils.FileUtils;

public class Main {

	private static final String outputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\LoadTest-TIMED.jmx";

	private static final String outputBackupFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\backup\\LoadTest-Backup"
			+ new GregorianCalendar().getTimeInMillis() + ".jmx";

	private static final String inputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\DinfLoadTest.jmx";

	private static final GregorianCalendar startTimeStamp = new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 4,
			22, 00, 00);
	private static final double runningTime = 10d * 60d * 1000d;
	private static final double breakTime = 10d * 60d * 1000d;

	public static void main(String[] args) {

		FileUtils readFileExample2 = new FileUtils();
		String readFile = readFileExample2.readFile(inputFilename);
		SchedulerChanger changer = new SchedulerChanger(runningTime, breakTime);
		String findAndReplaceSchedule = changer.findAndReplaceSchedule(readFile, startTimeStamp);
		// System.out.println(findAndReplaceSchedule);

		FileUtils.writeFile(findAndReplaceSchedule, outputFilename);
		FileUtils.writeFile(findAndReplaceSchedule, outputBackupFilename);

	}
}
