package day2;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class dayTwoB {

	public static void main(String[] args) throws IOException {
		String fileName = "/day2/input.txt";

		BufferedReader br = FileUtils.getStreamFromFile(fileName);
		String line;
		int result = 0;

		while ((line = br.readLine()) != null) {
			result += parseLine(line);
		}

		System.out.println("Result is: " + result);
	}

	private static int parseLine(String line) {
		String[] lineSplit = line.split(":");
		String data = lineSplit[1];
		List<Map<String, Integer>> rounds = Arrays.stream(data.split(";"))
		                                          .map(dayTwoB::parseRound)
		                                          .toList();

		Map<String, Integer> mergedMap = rounds.stream()
		                                       .flatMap(round -> round.entrySet().stream())
		                                       .collect(Collectors.toMap(Map.Entry::getKey,
		                                                                 Map.Entry::getValue,
		                                                                 (i, j) -> i > j ? i : j));

		return mergedMap.values().stream()
		                .reduce((a, b) -> a * b)
		                .orElse(0);
	}

	private static Map<String, Integer> parseRound(String data) {
		return Arrays.stream(data.split(","))
		             .map(pull -> {
			             String[] s = pull.split(" ");
			             return Map.entry(s[2], Integer.parseInt(s[1]));
		             })
		             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
