package day1;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class dayOneB {

	private static final Map<String, Integer> spelledOut = Map.of(
			"one", 1,
			"two", 2,
			"three", 3,
			"four", 4,
			"five", 5,
			"six", 6,
			"seven", 7,
			"eight", 8,
			"nine", 9);

	public static void main(String[] args) throws IOException {
		String fileName = "/day1/input.txt";

		BufferedReader br = FileUtils.getStreamFromFile(fileName);
		String line;
		int result = 0;

		while ((line = br.readLine()) != null) {
			line = resolveSpelledOutNumbers(line);
			line = line.replaceAll("\\D", "");
			result += Integer.parseInt("" + line.charAt(0) + line.charAt(line.length() - 1));
		}

		System.out.println(result);
	}

	public static String resolveSpelledOutNumbers(String line) {
		String result = "";

		for (int i = 0; i <= line.length() - 1; i++) {
			for (String number : spelledOut.keySet()) {
				if (line.startsWith(number, i)) {
					result += String.valueOf(spelledOut.get(number));
					break;
				}
			}
			result += String.valueOf(line.charAt(i));
		}

		return result;
	}
}
