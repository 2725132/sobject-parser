package com.sobjectparser.report;

import org.junit.Assert;
import org.junit.Test;

public class FileNameBuilderTest {
	@Test
	public void changeExtensionTest() {
		FileNameBuilder fnb = new FileNameBuilder();
		String input = "C:/input.xml";
		input = fnb.changeExtension(input, "pdf");
		Assert.assertEquals(input, "C:/input.pdf");

		input = "C:/Users/Felipe/Documents/Objects/Output/input.txt";
		input = fnb.changeExtension(input, "xml");
		Assert.assertEquals(input, "C:/Users/Felipe/Documents/Objects/Output/input.xml");

	}
	
	@Test
	public void changePathTest(){
		FileNameBuilder fnb = new  FileNameBuilder();
		String result = fnb.changePath("C:/", "C:/Users/Felipe/Documents/Objects/Output/input.txt");
		Assert.assertEquals(result, "C:/input.txt");
		
		String result2 = fnb.changePath("C:/Pasta/", "C://Documents/Objects/Output/input.txt");
		Assert.assertEquals(result2, "C:/Pasta/input.txt");
	}
}
