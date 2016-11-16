package com.sobjectparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public Document getDocumentFromXML(String XMLName) {
		FileInputStream xml;
		Document document = null;
		try {
			xml = new FileInputStream(XMLName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(xml);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Could not parse document");
			e.printStackTrace();
		}

		return document;

	}

	public List<String> getSobjectFields(Document document) {
		NodeList nodes = document.getElementsByTagName("note");
		List<String> fieldNames = new ArrayList<String>();
		for (int i = 0; i < nodes.getLength(); i++)
			fieldNames.add(nodes.item(i).getTextContent());
		return fieldNames;
	}

	public List<String> parse(String filename) {
		Document document = getDocumentFromXML(filename);
		return getSobjectFields(document);
	}

}
