package com.sobjectparser.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
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

	private Map<String, List<String>> getSobjectFields(Document document, List<String> expressions) {
		Map<String, List<String>> fieldsMapping = new HashMap<String, List<String>>();
	
		for(String expression : expressions)  {
			//Last field of expression to use as a key, if the expression is //object//field, so it will catch field
			String name = expression.substring(expression.lastIndexOf('/') + 1);
			fieldsMapping.put(name, getResultFromDocument(document, expression));	
		}
		return fieldsMapping;
	}
	
	private List<String> getResultFromDocument(Document document, String expression){
		List<String> content = new ArrayList<String>();
		try {
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		NodeList nodes;
		nodes = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++)	content.add(nodes.item(i).getTextContent());
		
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return content;
	}

	public Map<String, List<String>> read(String filename, List<String> expressions) {
		Document document = getDocumentFromXML(filename);
		return getSobjectFields(document, expressions);
	}

}
