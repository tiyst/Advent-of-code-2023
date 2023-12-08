package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

	public static BufferedReader getStreamFromFile(String fileName) {
		String fileLocation = System.getProperty("user.dir") + "/src" + fileName;

		try {
			return new BufferedReader(new FileReader(fileLocation));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(String.format("File %s not found", fileLocation));
		}
	}


	public static List<String> getLines(String fileName) throws IOException {
		String fileLocation = System.getProperty("user.dir") + "/src" + fileName;
		return Files.lines(Paths.get(fileLocation)).toList();
	}
}
