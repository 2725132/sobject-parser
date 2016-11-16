package com.sobjectparser;

import java.util.List;

import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.PDFReport;


public class BaseController {
	
	public static String SRCFOLDER = "C:/Users/fgcarva/Documents/sObject Project/sobject-parser-git/Input/";
	public static String DESTFOLDER = "C:/Users/fgcarva/Documents/sObject Project/sobject-parser-git/Output/";
	
	PDFReport pdfReport;
	DirectoryReader directoryReader;
	
	public BaseController(){
		pdfReport = new PDFReport();
		directoryReader = new DirectoryReader();
	}
	
	public void resolve(){
		List<String> filenames = directoryReader.read(SRCFOLDER);
		for(String file : filenames){
			System.out.println(file);
			
			//Invert slashes
			file = file.replace("\\", "/");
			
			PDFReport.MakeFieldsPDF(file, DESTFOLDER);
		}
		
	}
	public static void main(String[] args) {
		BaseController controller = new BaseController();
		controller.resolve();
	}
}
