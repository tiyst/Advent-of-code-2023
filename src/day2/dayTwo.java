package day2;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class dayTwo {

	private static Map<String, Integer> bagContains = Map.of(
			"red", 12,
			"green", 13,
			"blue", 14
	);

	public static void main(String[] args) throws IOException {
		String fileName = "/day2/input.txt";

		BufferedReader br = FileUtils.getStreamFromFile(fileName);
		String line;
		int result = 0;

		while ((line = br.readLine()) != null) {
			int result1 = parseLine(line);
			result += result1;
		}

		System.out.println("Result is: " + result);
	}

	private static int parseLine(String line) {
		String[] lineSplit = line.split(":");
		String gameId = lineSplit[0].replaceAll("\\D", "");
		String data = lineSplit[1];
		List<Map<String, Integer>> rounds = Arrays.stream(data.split(";"))
		                                          .map(dayTwo::parseRound)
		                                          .toList();

		Map<String, Integer> mergedMap = rounds.stream()
		                                       .flatMap(round -> round.entrySet().stream())
		                                       .collect(Collectors.toMap(Map.Entry::getKey,
		                                                                 Map.Entry::getValue,
		                                                                 (i, j) -> i > j ? i : j));

		boolean present = bagContains.entrySet().stream()
		                             .filter(pair -> mergedMap.containsKey(pair.getKey()))
		                             .anyMatch(pair -> mergedMap.get(pair.getKey()) > pair.getValue());

		return present ? 0 : Integer.parseInt(gameId);
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
