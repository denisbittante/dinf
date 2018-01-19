package ch.ffhs.dinf.osre.jasperreport.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario2;
import ch.ffhs.dinf.osre.engine.api.ActivityDetails;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario1;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Szenario2Impl extends AbstractScenario<PdfRequestScenario2> implements Scenario2 {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();;

	public Szenario2Impl(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildPdf() throws Exception {
		String masterReportFileName = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Scenario2.jrxml";

		String subReportFileName = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Scenario2.subreport.jrxml";

		JasperReport jasperReport = JasperCompileManager.compileReport(masterReportFileName);
		JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportFileName);

		createHashMapInput();

		parameters.put("subreportParameter", jasperSubReport);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);

		JasperExportManager.exportReportToPdfFile(print, getTempfile().getAbsolutePath());
	}

	@Override
	public void setAuthor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFont(FontType t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void createChapters() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSubject() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setKeywords() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle() {
		// TODO Auto-generated method stub

	}

	private void createHashMapInput() {

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		PdfRequestScenario2 model = getModel();

		List<ActivityGroup> data = model.getGroup();

		for (ActivityGroup activityGroup : data) {

			for (ActivityEntry activityEntry : activityGroup.getEntries()) {

				Field[] declaredFields = activityEntry.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					field.getName();
					try {

						String value = (String) activityEntry.getClass().getDeclaredMethod(
								"get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1),
								null).invoke(activityEntry, null);

						parameters.put("en_" + field.getName(), value);

						System.out.println("<field name=\"en_" + field.getName() + "\" class=\"java.lang.String\"/>");
						// System.out.println(value);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		list.add(parameters);

	}

}
