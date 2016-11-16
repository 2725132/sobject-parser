package com.sobjectparser.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sobjectparser.XMLReader;

public class PDFReport {
	FormattingPDF format;

	public PDFReport() {
		format = new FormattingPDF();
	}

	public static String adjustFileName(String fileName, String destiny){
		FileNameBuilder builder = new FileNameBuilder();
		fileName = builder.changeExtension(fileName, "pdf");
		fileName = builder.changePath(destiny, fileName);
		return fileName;
	}
	
	public static void MakeFieldsPDF(String fileName, String destiny) {
		XMLReader xml = new XMLReader();
		List<String> fieldNames = xml.parse(fileName);
		fileName = adjustFileName(fileName, destiny);
		Document document = new Document();
		try {			
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.setMargins(36, 72, 108, 108);
			SetTitle(fileName.substring(fileName.lastIndexOf("/"), fileName.lastIndexOf(".") ), document);

			for (String field : fieldNames) document.add(new Paragraph(field, FormattingPDF.paragraphFormat));
			
			
		} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
		} finally {
			document.close();
		}
	}

	public static void SetTitle(String text, Document doc) throws DocumentException {
		Paragraph title = new Paragraph(text, FormattingPDF.titleFormat);
		doc.add(title);
	}

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		MakeFieldsPDF("C:/Users/Felipe/Documents/Objects/Input/account.xml", "C:/Users/Felipe/Documents/Objects/Output/");
		MakeFieldsPDF("C:/Users/Felipe/Documents/Objects/Input/contact.xml", "C:/Users/Felipe/Documents/Objects/Output/");
		MakeFieldsPDF("C:/Users/Felipe/Documents/Objects/Input/PO.xml", "C:/Users/Felipe/Documents/Objects/Output/");
	}
}
