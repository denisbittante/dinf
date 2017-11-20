package ch.ffhs.dinf.osre.jasperreport.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.type.SectionTypeEnum;
import net.sf.jasperreports.swing.JRViewer;

public class ScenarioExample {

	public void createExampleReport() throws Exception {

		String reportSrcFile = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Blank_A4.jrxml";

		// First, compile jrxml file.
		JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
		// Fields for report


		System.out.println(jasperReport.getFields()[0].getName());

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("company", "MAROTHIA TECHS");
		parameters.put("receipt_no", "RE101".toString());
		parameters.put("name", "Khushboo");
		parameters.put("amount", "10000");
		parameters.put("receipt_for", "EMI Payment");
		parameters.put("date", "20-12-2016");
		parameters.put("contact", "98763178".toString());

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list.add(parameters);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);

		/** To see it in a viewer : **/

		// JRViewer viewer = new JRViewer(print);
		// viewer.setOpaque(true);
		// viewer.setVisible(true);
		// JFrame frame = new JFrame();
		// frame.add(viewer);
		// frame.setSize(700, 500);
		// frame.setVisible(true);

		if (print != null) {
			/**
			 * 1- export to PDF
			 */
			JasperExportManager.exportReportToPdfFile(print, "C://temp/sample_report.pdf");

			/**
			 * 2- export to HTML
			 */
			JasperExportManager.exportReportToHtmlFile(print, "C://temp/sample_report.html");

		}

	}
}
