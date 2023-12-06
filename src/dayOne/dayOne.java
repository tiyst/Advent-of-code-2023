package dayOne;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class First {

	public static void main(String[] args) throws IOException {
		String fileName = "/dayOne/input.txt";

		BufferedReader br = FileUtils.getStreamFromFile(fileName);
		String line;
		int result = 0;

		while ((line = br.readLine()) != null) {
			line = line.replaceAll("\\D","");
			result += Integer.parseInt("" + line.charAt(0) + line.charAt(line.length() - 1));
		}

		System.out.println(result);
	}

//	public String resolveSpelledOutNumbers(String line) {
//		spelledOut.keySet().stream()
//				.filter(line::contains)
//				.
//	}

}
