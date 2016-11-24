package com.sobjectparser.report;

import org.springframework.stereotype.Component;

@Component
public class FileNameBuilder {
	public String changeExtension(String fileName, String extension) {
		String changeFileName;
		if(fileName == null || extension == null ) changeFileName = "";
		if(fileName.contains("."))fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		if(extension != "") changeFileName = fileName + "." + extension;
		else changeFileName = fileName;
		
		return changeFileName;
	}

	public String changePath(String path, String fileName) {
		if(fileName.contains("/"))fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		fileName = path + fileName;
		return fileName;
	}
	
	public String getFileNameWithExtension(String fileName, String extension){
		return changePath("", changeExtension(fileName, extension));
	}

}
