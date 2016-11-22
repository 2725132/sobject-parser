package com.sobjectparser;

import java.util.ArrayList;
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
		List<String> fields = new ArrayList<String>();
		Map<String, List<String>> result = reader.read(BaseController.SRCFOLDER, fields );
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
	}
}
