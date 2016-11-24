package com.sobjectparser.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sobjectparser.BaseController;
import com.sobjectparser.directory.DirectoryReader;
import com.sobjectparser.report.FileNameBuilder;

import lombok.Data;

@Data
@Component
public class DataloaderBeanBuilder {

	private final static String SUCCESS_FOLDER = "C:\\Users\\fgcarva\\Desktop\\Felipe\\dataloader-config\\success\\";
	private final static String ERROR_FOLDER = "C:\\Users\\fgcarva\\Desktop\\Felipe\\dataloader-config\\error\\";

	@Autowired
	private DirectoryReader directoryReader;
	private XMLReader xmlReader;
	private List<String> expressions;

	private Document doc;
	private Element beans;
	private DocumentBuilder docBuilder;

	@Autowired
	private FileNameBuilder fileNameBuilder;

	@PostConstruct
	public void init() {

		try {
			System.out.println("Initializing..");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			beans = doc.createElement("beans");
			doc.appendChild(beans);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void composeXML(String fileName, Map<String, List<String>> sourceXML) {
		Element bean = doc.createElement("bean");

		bean.setAttribute("id", fileName);
		bean.setAttribute("class", "com.salesforce.dataloader.process.ProcessRunner");
		bean.setAttribute("singleton", "false");

		Element description = doc.createElement("description");
		bean.appendChild(description);

		Element name = doc.createElement("property");
		name.setAttribute("name", "name");
		name.setAttribute("value", "csv" + fileName);
		bean.appendChild(name);

		Element configOverrideMap = doc.createElement("property");
		configOverrideMap.setAttribute("name", "configOverrideMapw");
		Element map = doc.createElement("map");
		configOverrideMap.appendChild(map);

		Element processOutputError = doc.createElement("entry");
		processOutputError.setAttribute("key", "process.outputError");
		processOutputError.setAttribute("value",
				ERROR_FOLDER + fileNameBuilder.getFileNameWithExtension(fileName, "csv"));
		map.appendChild(processOutputError);

		Element dataAccessName = doc.createElement("entry");
		dataAccessName.setAttribute("key", "dataAccess.name");
		dataAccessName.setAttribute("value",
				SUCCESS_FOLDER + fileNameBuilder.getFileNameWithExtension(fileName, "csv"));
		map.appendChild(dataAccessName);

		Element sfdcEntity = doc.createElement("entry");
		sfdcEntity.setAttribute("key", "sfdc.entity");
		sfdcEntity.setAttribute("value", fileName);
		map.appendChild(sfdcEntity);

		Element sfdcExtractionSOQL = doc.createElement("entry");
		sfdcExtractionSOQL.setAttribute("key", "sfdc.extractionSOQL");
		sfdcExtractionSOQL.setAttribute("value", QueryBuilder.buildQuery(fileNameBuilder.getFileNameWithExtension(fileName, ""), sourceXML));
		map.appendChild(sfdcExtractionSOQL);

		Element processOperation = doc.createElement("entry");
		processOperation.setAttribute("key", "process.operation");
		processOperation.setAttribute("value", "extract");
		map.appendChild(processOperation);

		bean.appendChild(configOverrideMap);
		
		beans.appendChild(bean);

	}

	public void buildDocument(String fileName, Map<String, List<String>> sourceXML) {
			System.out.println("Making xml.....");
			fileName = fileNameBuilder.changeExtension(fileName, "xml");
			fileName = fileNameBuilder.changePath(BaseController.DESTFOLDER, fileName);
			composeXML(fileNameBuilder.getFileNameWithExtension(fileName, ""), sourceXML);
	}
	
	
	public void gustavo(){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(BaseController.DESTFOLDER + "process-conf.xml"));
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
