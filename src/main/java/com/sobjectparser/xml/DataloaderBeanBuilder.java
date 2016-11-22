package com.sobjectparser.xml;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import com.sobjectparser.BaseController;
import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.FileNameBuilder;

@Component
public class DataloaderBeanBuilder {
	@Autowired
	private DirectoryReader directoryReader;
	private XMLReader xmlReader;
	private List<String> expressions;
	
	@Autowired
	private FileNameBuilder fileNameBuilder;
	
	public void buildBean(Map<String, List<String>> fieldsMapping) throws ParserConfigurationException, TransformerException {
		List<String> fileNames = directoryReader.read(BaseController.SRCFOLDER);
		for (String fileName : fileNames) {
			xmlReader.read(fileName, expressions);
		}
	}

	public void buildDocument(String fileName, Map<String, List<String>> sourceXML) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			System.out.println("Making xml.....");
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			fileNameBuilder.changeExtension(fileName, "xml");
			fileNameBuilder.changePath(BaseController.DESTFOLDER, fileName);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		docBuilder.newDocument();

	}
}
