package day4;

import utils.FileUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DayFour {

	public static final String FILE_NAME = "/day4/input.txt";

	private List<String> lines;

	public static void main(String[] args) throws IOException {
		List<String> lines = FileUtils.getLines(FILE_NAME);
		DayFour runner = new DayFour(lines);
		System.out.println(runner.parseLines());
	}

	public DayFour(List<String> lines) {
		this.lines = lines;
	}

	private Integer parseLines() {
		return lines.stream()
		            .map(this::parseLine)
		            .reduce(Integer::sum)
		            .orElse(0);
	}

	private int parseLine(String line) {
		String playSpace = line.split(":")[1];
		String[] split = playSpace.split("\\|");
		List<Integer> winning = parseNumbersFromString(split[0]);
		List<Integer> ownedNumbers = parseNumbersFromString(split[1]);

		List<Integer> winningNumbers = ownedNumbers.stream()
		                                           .filter(winning::contains)
		                                           .toList();

		int points = (int) Math.pow(2, winningNumbers.size() - 1);
		System.out.println(line.split(":")[0] + " has points: " + points);
		return points;
	}

	private List<Integer> parseNumbersFromString(String line) {
		String[] s = line.split(" ");
		return Arrays.stream(s)
		             .filter(num -> !num.isEmpty())
		             .map(Integer::parseInt)
		             .toList();
	}
}
