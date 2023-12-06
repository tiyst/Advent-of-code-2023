package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {

	public static BufferedReader getStreamFromFile(String fileName) {
		String fileLocation = System.getProperty("user.dir") + "/src" + fileName;

		try {
			return new BufferedReader(new FileReader(fileLocation));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(String.format("File %s not found", fileLocation));
		}
	}
}
