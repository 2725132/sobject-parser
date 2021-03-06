package com.sobjectparser.directory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sobjectparser.BaseController;

@Component
public class DirectoryReader {

	public List<String> read(String folderPath) {
		List<String> names = new ArrayList<String>();
		System.out.println("Reading input directory.....");
		File[] files = new File(folderPath).listFiles();
		
		for (File file : files) {
			String filePath = file.getAbsolutePath();
			filePath = filePath.replace("\\", "/");
			names.add(filePath);
		}

		return names;
	}

	public static void main(String[] args) {
		DirectoryReader directoryReader = new DirectoryReader();
		System.out.println(directoryReader.read(BaseController.SRCFOLDER));
	}
}
