package ch.ffhs.dinf.osre.jasperreport.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario3;
import ch.ffhs.dinf.osre.engine.api.ActivityEntry;
import ch.ffhs.dinf.osre.engine.api.ActivityGroup;
import ch.ffhs.dinf.osre.engine.api.Contact;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario3;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Szenario3Impl extends AbstractScenario<PdfRequestScenario3> implements Scenario3 {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	public Szenario3Impl(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
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

	@Override
	protected void buildPdf() throws Exception {
		String reportSrcFile = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Scenario3.jrxml";
		JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

		createHashMapInput();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);

		JasperExportManager.exportReportToPdfFile(print, getTempfile().getAbsolutePath());
	}

	private void createHashMapInput() {

		PdfRequestScenario3 model = getModel();

		List<Contact> data = model.getContacts();

		for (Contact contact : data) {

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			Field[] declaredFields = contact.getClass().getDeclaredFields();
			for (Field field : declaredFields) {

				field.getName();
				try {

					String value = (String) contact.getClass().getDeclaredMethod(
							"get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), null)
							.invoke(contact, null);

					parameters.put(field.getName(), value);

					System.out.println("<field name=\"" + field.getName() + "\" class=\"java.lang.String\"/>");
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

			list.add(parameters);
		}

	}
}
