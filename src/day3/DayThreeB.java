package day3;

import utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

import static day3.DayThreeB.Operation.ADD;
import static day3.DayThreeB.Operation.MULT;


/**
 * At first I've misread the assignment and thought we're supposed to do everything the same as 3a
 * except for the case when there's an '*' symbol with 2 neighbours in which case we should multiply.
 * That explains why there's an Enum with a BinaryOperator. I've decided to leave that behind as an
 * example of how this could've been solved if the assignment was that way.
 */
public class DayThreeB {

	public static final String FILE_NAME = "/day3/input.txt";
	public static final Pattern IGNORED_CHARS = Pattern.compile("[*]");

	private final char[][] inputField;

	public static void main(String[] args) throws IOException {
		DayThreeB runner = new DayThreeB();
//		runner.debugPrint();
		System.out.println("Result is: " + runner.calc());
//		runner.debugPrint();
	}

	public DayThreeB() throws IOException {
		inputField = getField();
	}

	private int calc() {
		int result = 0;
		for (int i = 0; i < inputField.length; i++) {
			for (int j = 0; j < inputField[i].length; j++) {
				String ch = String.valueOf(inputField[i][j]);
				if (IGNORED_CHARS.matcher(ch).matches()) {
					result += getSumForSymbol(i, j, resolveOperation(ch));
				}
			}
		}

		return result;
	}

	private Operation resolveOperation(String ch) {
		return ch.equals("*") ? MULT : ADD;
	}

	private int getSumForSymbol(int x, int y, Operation operation) {
		List<Integer> extracts = new ArrayList<>();

		for (int i = Math.max(x - 1, 0); i <= (Math.min(x + 1, inputField.length - 1)); i++) {
			for (int j = Math.max(y - 1, 0); j <= (Math.min(y + 1, inputField[i].length - 1)); j++) {
				char ch = inputField[i][j];
				if (Character.isDigit(ch)) {
					extracts.add(extractInteger(i, j));
				}

			}
		}

		return extracts.size() == 2
				? 0
				: extracts.stream().reduce(operation.binaryOperator).orElse(0);
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

	enum Operation {
		// Read top of the file
		ADD(Integer::sum),
		MULT((a,b) -> a*b);

		private final BinaryOperator<Integer> binaryOperator;

		Operation(BinaryOperator<Integer> operator) {
			this.binaryOperator = operator;
		}
	}
}
