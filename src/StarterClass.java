import utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class StarterClass {

	// TODO: Change day folder
	public static final String FILE_NAME = "/dayX/input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = FileUtils.getLines(FILE_NAME);
		StarterClass runner = new StarterClass();
	}

	public StarterClass() throws IOException {

	}
}
