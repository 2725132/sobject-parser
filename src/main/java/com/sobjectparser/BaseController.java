package com.sobjectparser;

import java.util.List;

import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.PDFReport;

import lombok.Data;
@Data
public class BaseController {
	
	private static String SRCFOLDER = "C:/Users/Felipe/Documents/Objects/Input/";
	private static String DESTFOLDER = "C:/Users/Felipe/Documents/Objects/Output/";
	
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
