import java.util.ArrayList;
import java.util.Collection;

/**
 * This class can encode and decode a string using Vigenere cipher based on a
 * group of keys.
 * 
 * @author Zhen Chen
 *
 */

public class Vigenere implements Cipher {
	private ArrayList<Integer> keyList;

	public Vigenere(int[] keyList) {
		this.keyList = arrayToList(keyList);
	}

	public Vigenere(Collection<Integer> keyList) {
		this.keyList = new ArrayList<Integer>(keyList);
	}

	private static ArrayList<Integer> arrayToList(int[] array) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
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
		ArrayList<Integer> list = arrayToList(keyList);
		return encode(txt, list);
	}

	public static String encode(String txt, ArrayList<Integer> keyList) {
		char[] buffer = txt.toCharArray();
		char letter;
		int count = 0;
		int size = keyList.size();
		int code;
		int base;
		int key;

		for (int i = 0; i < buffer.length; i++) {
			letter = buffer[i];
			code = (int) letter;
			key = keyList.get(count % size);

			if (letter >= 'a' && letter <= 'z' || letter >= 'A' && letter <= 'Z') {
				if (letter >= 'a' && letter <= 'z') {
					base = (int) 'a';
				} else {
					base = (int) 'A';
				}
				buffer[i] = (char) ((code + key - base) % 26 + base);
				count++;
			}
		}

		return String.valueOf(buffer);
	}

	public String decode(String txt) {
		return decode(txt, keyList);
	}

	public static String decode(String txt, int[] keyList) {
		ArrayList<Integer> list = arrayToList(keyList);
		return decode(txt, list);
	}

	public static String decode(String txt, ArrayList<Integer> keyList) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < keyList.size(); i++) {
			list.add(26 - keyList.get(i));
		}
		return encode(txt, list);
	}
}
