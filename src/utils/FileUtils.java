package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {

	public static BufferedReader getStreamFromFile(String fileName) {
		try {
			return new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(String.format("File %s not found", fileName));
		}
	}
}
