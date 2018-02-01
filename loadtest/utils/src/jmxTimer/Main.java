package jmxTimer;

import java.util.GregorianCalendar;

public class Main {

	private static final String outputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\LoadTest-TIMED.jmx";
	private static final String inputFilename = "C:\\sandbox\\dinf\\dinf\\loadtest\\jmeter\\DinfLoadTest.jmx";

	private static final GregorianCalendar startTimeStamp = new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 1,
			23, 30, 00);
	private static final double runningTime = 10d * 60d * 1000d;
	private static final double breakTime = 10d * 60d * 1000d;

	public static void main(String[] args) {

		ReadFileExample readFileExample2 = new ReadFileExample();
		String readFile = readFileExample2.readFile(inputFilename);
		SchedulerChanger changer = new SchedulerChanger(runningTime, breakTime);
		String findAndReplaceSchedule = changer.findAndReplaceSchedule(readFile, startTimeStamp);
		// System.out.println(findAndReplaceSchedule);

		WriteToFileExample.writeFile(findAndReplaceSchedule, outputFilename);

	}
}
