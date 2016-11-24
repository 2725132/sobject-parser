package com.sobjectparser;

import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.PDFReport;
import com.sobjectparser.xml.DataloaderBeanBuilder;
import com.sobjectparser.xml.XMLReader;

@Controller
public class BaseController {
	

	public static String SRCFOLDER = "Input/";
	public static String DESTFOLDER = "Output/";
	
	@Autowired
	PDFReport pdfReport;
	
	@Autowired
	DataloaderBeanBuilder beanBuilder;
	
	@Autowired
	DirectoryReader directoryReader;
	
	@Autowired
	XMLReader xmlReader;
	
	
	@Value("#{'${xmlExpressions}'.split(',')}") 
	List<String> expressions;
	
	public BaseController(){		
	}
	
	public void resolveForPDF(){
		System.out.println(expressions);
		List<String> filenames = directoryReader.read(SRCFOLDER);
		for(String fileName : filenames){
			System.out.println(fileName);
			
			Map<String, List<String>> fieldsMapping = xmlReader.read(fileName, expressions);
			PDFReport.MakeFieldsPDF(fileName, fieldsMapping);
		}
		
	}
	
	
	public void resolveForDataloader(){
		System.out.println(expressions);
		List<String> filenames = directoryReader.read(SRCFOLDER);
		for(String fileName : filenames){
			System.out.println(fileName);
			Map<String, List<String>> fieldsMapping = xmlReader.read(fileName, expressions);
			beanBuilder.buildDocument(fileName, fieldsMapping);
		}
		beanBuilder.gustavo();
	}
	public static void main(String[] args) {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("context.xml");
		BaseController controller = (BaseController) context.getBean(BaseController.class);
		
		
		//controller.resolveForPDF();
		controller.resolveForDataloader();
	}
}
