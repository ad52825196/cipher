import java.util.ArrayList;
import java.util.Collection;

/**
 * This class can encode and decode a string using Vigenere cipher based on a
 * group of keys;
 * 
 * @author Zhen Chen
 *
 */

public class Vigenere implements Cipher {
	private ArrayList<Integer> keyList;

	public Vigenere(int[] keyList) {
		this.keyList = new ArrayList<Integer>();
		for (int i = 0; i < keyList.length; i++) {
			this.keyList.add(keyList[i]);
		}
	}

	public Vigenere(Collection<Integer> keyList) {
		this.keyList = new ArrayList<Integer>(keyList);
	}

	public ArrayList<Integer> getKeyList() {
		return keyList;
	}

	public void setKeyList(Collection<Integer> keyList) {
		this.keyList = new ArrayList<Integer>(keyList);
	}

	public void addKey(int key) {
		this.keyList.add(key);
	}

	public void addKeyList(int[] keyList) {
		for (int i = 0; i < keyList.length; i++) {
			this.keyList.add(keyList[i]);
		}
	}

	public void addKeyList(Collection<Integer> keyList) {
		this.keyList.addAll(keyList);
	}

	public String encode(String txt) {
		return encode(txt, keyList);
	}

	public static String encode(String txt, int[] keyList) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < keyList.length; i++) {
			temp.add(keyList[i]);
		}
		return encode(txt, temp);
	}

	public static String encode(String txt, ArrayList<Integer> keyList) {
		char[] buffer = txt.toCharArray();
		char letter;
		int code;
		int base;
		int key;
		int count = 0;

		for (int i = 0; i < buffer.length; i++) {
			letter = buffer[i];
			code = (int) letter;
			key = keyList.get(count % keyList.size());

			if (letter >= 'a' && letter <= 'z') {
				count++;
				base = (int) 'a';
				buffer[i] = (char) ((code + key - base) % 26 + base);
			} else if (letter >= 'A' && letter <= 'Z') {
				count++;
				base = (int) 'A';
				buffer[i] = (char) ((code + key - base) % 26 + base);
			}
		}

		return String.valueOf(buffer);
	}

}
