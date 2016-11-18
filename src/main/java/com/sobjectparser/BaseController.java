package com.sobjectparser;

import java.util.List;
import java.util.Map;

import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.PDFReport;
import com.sobjectparser.xml.XMLReader;


public class BaseController {
	
	public static String SRCFOLDER = "Input/";
	public static String DESTFOLDER = "Output/";
	
	PDFReport pdfReport;
	DirectoryReader directoryReader;
	XMLReader xmlReader;
	public BaseController(){
		pdfReport = new PDFReport();
		directoryReader = new DirectoryReader();
		xmlReader = new XMLReader();
	}
	
	public void resolveForPDF(){
		List<String> filenames = directoryReader.read(SRCFOLDER);
		for(String fileName : filenames){
			System.out.println(fileName);
			
			Map<String, List<String>> fieldsMapping = xmlReader.read(fileName);
			PDFReport.MakeFieldsPDF(fileName, fieldsMapping);
		}
		
	}
	public static void main(String[] args) {
		BaseController controller = new BaseController();
		controller.resolveForPDF();
	}
}
