package com.sobjectparser;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import com.sobjectparser.xml.XMLReader;

public class XMLReaderTest {
	@Test
	public void getDocumentFromXMLTest() {
		XMLReader reader = new XMLReader();
		Document doc = reader.getDocumentFromXML(BaseController.SRCFOLDER);
		Assert.assertNotNull(doc);

	}

	@Test
	public void parseTest() {
		XMLReader reader = new XMLReader();
		Map<String, List<String>> result = reader.read(BaseController.SRCFOLDER);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
	}
}
