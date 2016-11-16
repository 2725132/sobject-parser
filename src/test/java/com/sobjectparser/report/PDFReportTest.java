package com.sobjectparser.report;

import org.junit.Assert;
import org.junit.Test;

public class PDFReportTest {
	@Test
	public void adjustNameTest(){
		PDFReport report = new PDFReport();
		String result = report.adjustFileName("input.xml", "C:/Users/Felipe/Documents/Objects/Output/");
		Assert.assertEquals(result, "C:/Users/Felipe/Documents/Objects/Output/input.pdf" );
		System.out.println(result);
		
		String result2 = report.adjustFileName("input.txt", "C:/Destiny/");
		Assert.assertEquals(result2 , "C:/Destiny/input.pdf" );
		System.out.println(result2);
	}
}
