/**
 * This class can encode and decode a string using Base64.
 * 
 * @author Zhen Chen
 *
 */

public class Base64 implements Cipher {

	public String encode(String txt) {
		java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
		byte[] bytes = encoder.encode(txt.getBytes());
		return new String(bytes);
	}

	public String decode(String txt) {
		java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
		byte[] bytes = decoder.decode(txt.getBytes());
		return new String(bytes);
	}

}
