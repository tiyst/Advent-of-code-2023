package first;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class First {

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") + "/src/first/input.txt";

		BufferedReader br = FileUtils.getStreamFromFile(fileName);
		String line;
		int result = 0;

		while ((line = br.readLine()) != null) {
			line = line.replaceAll("\\D","");
			result += Integer.parseInt("" + line.charAt(0) + line.charAt(line.length() - 1));
		}

		System.out.println(result);
	}

}
