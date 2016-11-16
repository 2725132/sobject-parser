package com.sobjectparser.report;

public class FileNameBuilder {
	public String changeExtension(String fileName, String extension) {
		fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		String changeFileName = fileName + "." + extension;
		return changeFileName;
	}

	public String changePath(String path, String fileName) {
		if(fileName.contains("/"))fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		fileName = path + fileName;
		return fileName;
	}
	

}
