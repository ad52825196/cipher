import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
	private List<String> input;

	public BruteForce() {
		input = new ArrayList<String>();
	}

	public BruteForce(final List<Object> list) {
		this();
		for (final Object o : list) {
			input.add(o.toString());
		}
	}

	public BruteForce(String filename) {
		this();
		readFile(filename);
	}

	public void readFile(String filename) {
		File file = new File(filename);
		String line;

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (!line.equals("")) {
					input.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + filename);
		}
	}

	public void match(String expect, String type, Pattern p) {
		String txt;
		String result;

		for (String s : input) {
			txt = p.f(s);
			result = Hash.getHash(txt, type);
			if (result.equals(expect)) {
				System.out.println("Success! Plain text is: " + s);
				return;
			}
		}

		System.out.println("Failed!");
	}

	public static void main(String[] args) {
		final String inputFileName = "list.txt";
		final String type = "MD5";
		final String expect = "";
		BruteForce bf = new BruteForce(inputFileName);

		// change this line to transform each candidate string
		final Pattern p = s -> s;

		bf.match(expect, type, p);
	}

}
