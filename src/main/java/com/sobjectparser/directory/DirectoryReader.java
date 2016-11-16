package com.sobjectparser.directory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {
	
	
	
	public List<String> read(String folderPath){
		List<String> names = new ArrayList<String>();
		File[] files = new File(folderPath).listFiles();
		for(File file: files) names.add(file.getAbsolutePath());
		
		return names;
	}
	
	public static void main(String[] args) {
		DirectoryReader directoryReader = new DirectoryReader();
		System.out.println(directoryReader.read("C:/Users/Felipe/Documents/Objects/Input/"));
	}
}
