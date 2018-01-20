package ch.ffhs.dinf.osre.jasperreport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ch.ffhs.dinf.osre.engine.AbstractScenario;
import ch.ffhs.dinf.osre.engine.Scenario2;
import ch.ffhs.dinf.osre.engine.api.PdfRequestScenario2;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class Szenario2Impl extends AbstractScenario<PdfRequestScenario2> implements Scenario2 {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();;

	public Szenario2Impl(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildPdf() throws Exception {
		String masterReportFileName = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Scenario2.jrxml";

		String subReportFileName = "C:\\sandbox\\dinf\\dinf\\app\\jasper-report\\src\\main\\resources\\Scenario2Subreport.jrxml";

		JasperReport jasperReport = JasperCompileManager.compileReport(masterReportFileName);
		JasperReport jasperSubReport = JasperCompileManager.compileReport(subReportFileName);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("subreportParameter", jasperSubReport);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(getModel().getGroup());

		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		final SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setMetadataTitle(FILENAME);
		configuration.setMetadataAuthor(AUTHOR);
		configuration.setMetadataCreator(getName());
		configuration.setMetadataSubject(SUBJECT);
		configuration.setMetadataKeywords(KEYWORDS);

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(getTempfile().getAbsolutePath()));
		exporter.setConfiguration(configuration);

		exporter.exportReport();
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

}
