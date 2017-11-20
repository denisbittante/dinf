package ch.ffhs.dinf.osre.itext.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;

public class ScenarioExample {

	
	 public static void createPdf() throws Exception {              
	      // Creating a PdfWriter       
	      String dest = "C:/temp/sample.pdf";       
	      PdfWriter writer = new PdfWriter(dest); 
	   
	      // Creating a PdfDocument       
	      PdfDocument pdfDoc = new PdfDocument(writer);              
	   
	      // Adding a new page 
	      pdfDoc.addNewPage();               
	   
	      // Creating a Document        
	      Document document = new Document(pdfDoc);               
	   
	      
	      // Creating an Area Break          
	      AreaBreak aB = new AreaBreak();           
	   
	      // Adding area break to the PDF       
	      document.add(aB);          
	      
	      // Closing the document    
	      document.close();              
	      System.out.println("PDF Created");    
	   } 
	
	
	
}
