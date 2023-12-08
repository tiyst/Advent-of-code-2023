package day3;

import utils.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class DayThree {

	public static final String FILE_NAME = "/day3/input.txt";
	public static final Pattern IGNORED_CHARS = Pattern.compile("[0-9.]"); //any digit and dots

	private final char[][] inputField;

	public static void main(String[] args) throws IOException {
		DayThree runner = new DayThree();
//		runner.debugPrint();
		System.out.println("Result is: " + runner.calc());
//		runner.debugPrint();
	}

	public DayThree() throws IOException {
		inputField = getField();
	}

	private int calc() {
		int result = 0;
		for (int i = 0; i < inputField.length; i++) {
			for (int j = 0; j < inputField[i].length; j++) {
				String ch = String.valueOf(inputField[i][j]);
				if (!IGNORED_CHARS.matcher(ch).matches()) {
					result += getSumForSymbol(i, j);
				}
			}
		}

		return result;
	}

	private int getSumForSymbol(int x, int y) {
		int sum = 0;
		for (int i = Math.max(x - 1, 0); i <= (Math.min(x + 1, inputField.length - 1)); i++) {
			for (int j = Math.max(y - 1, 0); j <= (Math.min(y + 1, inputField[i].length - 1)); j++) {
				char ch = inputField[i][j];
				if (Character.isDigit(ch)) {
					int extract = extractInteger(i, j);
					sum += extract;
				}

			}
		}

		return sum;
	}

	private int extractInteger(int x, int y) {
		String res = String.valueOf(inputField[x][y]);
		int pivot = y;
		inputField[x][pivot] = '.';

		while (--pivot >= 0 && Character.isDigit(inputField[x][pivot])) {
			res = inputField[x][pivot] + res;
			inputField[x][pivot] = '.';
		}

		pivot = y;
		while (++pivot <= inputField.length - 1 && Character.isDigit(inputField[x][pivot])) {
			res = res + inputField[x][pivot] ;
			inputField[x][pivot] = '.';
		}

		return Integer.parseInt(res);
	}

	private char[][] getField() throws IOException {
		List<String> lines = FileUtils.getLines(FILE_NAME);
		char[][] field = new char[lines.size()][lines.get(0).length()];

		for (int i = 0; i < lines.size(); i++) {
			field[i] = lines.get(i).toCharArray();
		}

		return field;
	}

	private void debugPrint() {
		for (int i = 0; i < inputField.length; i++) {
			for (int j = 0; j < inputField[i].length; j++) {
				System.out.print(inputField[i][j]);
			}
			System.out.println("");
		}
	}
}
