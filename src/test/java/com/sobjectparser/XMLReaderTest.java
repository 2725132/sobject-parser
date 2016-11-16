package com.sobjectparser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

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
		List<String> result = reader.parse(BaseController.SRCFOLDER);
		Assert.assertNotNull(result);
		Assert.assertEquals(8, result.size());
	}
}
