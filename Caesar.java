/**
 * This class can encode and decode a string using Caesar cipher based on a key.
 * 
 * @author Zhen Chen
 *
 */

public class Caesar implements Cipher {
	private int key;

	public Caesar() {
		key = 0;
	}

	public Caesar(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String encode(String txt) {
		return encode(txt, key);
	}

	public static String encode(String txt, int key) {
		char[] buffer = txt.toCharArray();
		char letter;
		int code;
		int base;

		for (int i = 0; i < buffer.length; i++) {
			letter = buffer[i];
			code = (int) letter;

			if (letter >= 'a' && letter <= 'z') {
				base = (int) 'a';
				buffer[i] = (char) ((code + key - base) % 26 + base);
			} else if (letter >= 'A' && letter <= 'Z') {
				base = (int) 'A';
				buffer[i] = (char) ((code + key - base) % 26 + base);
			}
		}

		return String.valueOf(buffer);
	}

	public String decode(String txt) {
		return decode(txt, key);
	}

	public static String decode(String txt, int key) {
		return encode(txt, 26 - key);
	}

}
