package com.sobjectparser.report;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sobjectparser.directory.DirectoryReader;


public class DirectoryReaderTest {
	@Test
	public void readTest(){
		DirectoryReader directoryReader = new DirectoryReader();
		List<String> result = directoryReader.read("C:/Users/Felipe/Documents/Objects/Input/");
		Assert.assertNotNull(result);
	}
}
