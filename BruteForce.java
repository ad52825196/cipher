import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class uses a brute force method to decrypt or decode a given string. It
 * reads a list of candidate strings from a file and use a user specified method
 * to transform each of these strings, which may add some fixed content to the
 * string. Then it will encode each string according to a user specified
 * encryption or encoding algorithm and compare the result string with the given
 * string. If they are identical, then the plain text of the given string is
 * found.
 * 
 * @author Zhen Chen
 *
 */

public class BruteForce {
	private List<String> input;
	private String result;

	public static void main(String[] args) {
		final String inputFileName = "dict.txt";

		// change this line to specify your target string
		final String expect = "";

		Cipher c = new Hash("MD5");
		BruteForce bf = new BruteForce(inputFileName);

		// change this line to transform each candidate string
		final Pattern p = s -> s;

		if (bf.match(expect, c, p)) {
			System.out.println("Success! The plain text is: " + bf.result());
		} else {
			System.out.println("Failed!");
		}
	}

	public BruteForce() {
		input = new ArrayList<String>();
		result = "";
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

	public String result() {
		return result;
	}

	public boolean match(String expect, Cipher cipher, Pattern p) {
		String txt;
		String cipherTxt;

		for (String s : input) {
			txt = p.f(s);
			cipherTxt = cipher.encode(txt);
			if (cipherTxt.equals(expect.toLowerCase())) {
				result = s;
				return true;
			}
		}

		return false;
	}

}
