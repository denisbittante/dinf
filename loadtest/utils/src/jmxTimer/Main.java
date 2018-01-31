package jmxTimer;

public class Main {

	public static void main(String[] args) {

		ReadFileExample readFileExample2 = new ReadFileExample();
		String readFile = readFileExample2.readFile();
		SchedulerChanger changer = new SchedulerChanger();
		String findAndReplaceSchedule = changer.findAndReplaceSchedule(readFile);
	//	System.out.println(findAndReplaceSchedule);

		WriteToFileExample.writeFile(findAndReplaceSchedule);

	}
}
