package ch.ffhs.dinf.osre.jasperreport.service;

import org.junit.Test;

public class ScenarioExampleTest {

	@Test
	public void test() {

		ScenarioExample example = new ScenarioExample();

		try {
			example.createExampleReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
