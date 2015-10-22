import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash implements Cipher {
	private String hashType;

	public Hash(String hashType) {
		this.hashType = hashType;
	}

	public String getHashType() {
		return hashType;
	}

	public void setHashType(String hashType) {
		this.hashType = hashType;
	}

	public String encode(String txt) {
		return encode(txt, hashType);
	}

	public static String encode(String txt, String hashType) {
		try {
			MessageDigest md = MessageDigest.getInstance(hashType);
			byte[] array = md.digest(txt.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// error action
		}
		return null;
	}

	public static String md5(String txt) {
		return encode(txt, "MD5");
	}

	public static String sha1(String txt) {
		return encode(txt, "SHA1");
	}

}
