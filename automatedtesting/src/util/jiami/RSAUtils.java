package util.jiami;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

//import org.springframework.security.crypto.codec.Base64;

/**
 * <p>
 * RSA鍏挜/绉侀挜/绛惧悕宸ュ叿鍖�
 * </p>
 * <p>
 * 缃楃撼寰仿锋潕缁存柉鐗癸紙Ron [R]ivest锛夈�侀樋杩疯惃鑾皵锛圓di [S]hamir锛夊拰浼︾撼寰仿烽樋寰锋浖锛圠eonard [A]dleman锛�
 * </p>
 * <p>
 * 瀛楃涓叉牸寮忕殑瀵嗛挜鍦ㄦ湭鍦ㄧ壒娈婅鏄庢儏鍐典笅閮戒负BASE64缂栫爜鏍煎紡<br/>
 * 鐢变簬闈炲绉板姞瀵嗛�熷害鏋佸叾缂撴參锛屼竴鑸枃浠朵笉浣跨敤瀹冩潵鍔犲瘑鑰屾槸浣跨敤瀵圭О鍔犲瘑锛�<br/>
 * 闈炲绉板姞瀵嗙畻娉曞彲浠ョ敤鏉ュ瀵圭О鍔犲瘑鐨勫瘑閽ュ姞瀵嗭紝杩欐牱淇濊瘉瀵嗛挜鐨勫畨鍏ㄤ篃灏变繚璇佷簡鏁版嵁鐨勫畨鍏�
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

	/**
	 * 鍔犲瘑绠楁硶RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 绛惧悕绠楁硶
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 鑾峰彇鍏挜鐨刱ey
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 鑾峰彇绉侀挜鐨刱ey
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA鏈�澶у姞瀵嗘槑鏂囧ぇ灏�
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA鏈�澶цВ瀵嗗瘑鏂囧ぇ灏�
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;
	
	/**
	 *  娴嬭瘯鍏挜 
	 */
	private static final String TEST_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFYyhak5slMxWMrlBO/Eau5P7EvxdBwXrQPdVBKO2w7MiZ3b98iRI3oy2ELqorc0UFIoUyH3tirzcc0191m6Zt61/OLcUWUnzxOGfaNtG9MmPOf15EU7K3fMD4LTKrlWaQzns7QZmdFz/jkRGBvZ3HNoPtuPUT7sb1gCjUJaeefwIDAQAB";
	/**
	 * 	娴嬭瘯绉侀挜
	 */
	private static final String TEST_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMVjKFqTmyUzFYyuUE78Rq7k/sS/F0HBetA91UEo7bDsyJndv3yJEjejLYQuqitzRQUihTIfe2KvNxzTX3Wbpm3rX84txRZSfPE4Z9o20b0yY85/XkRTsrd8wPgtMquVZpDOeztBmZ0XP+OREYG9ncc2g+249RPuxvWAKNQlp55/AgMBAAECgYEAkWr93c0E7aD27U+2hppBELRQJW6Kmb0K18PWCk0237NyDjlZy0vIigjDjbA7Wgtv+9p0unqLEib3uVrX5vMm5mSXk2cWX/474I8UkSvKSje9uCtX57nDJ1WMAmTeeD/A5bTeznvNuAodsm1SxSl+6g+IPDlvSG2Qw3rkISg4L6ECQQDp+XQXTSDUz35lfyunEkqML3BqvlU13WLoOIqxQqx25O8I49su7mHO5fYAd7zQvBU5EAoOMguplBX9vOuBF6iZAkEA1/f+Bxqigi9hLwie8zLNVm2hrYHrA7BblgKqHYBDVft6nHRJ8vCFdgaTJYhaszGG/KqZOeQ+89in/KDYcrb21wJBAN+IJ1UrprYqFkO5n2bansYXfHs+pAH2JExf2IFJhaOBTK1do0XPETqtkL0ZqBZz2oLNxA2T2niEtg3Ys9Z9V+ECQErsbeRpCRfA+CYpB3u3lCT3w6898xpEhIF2Sy4Q4UtjAxZkAYOWjbZ0cXgD5fNkqz/cr2u2E2DlOOIbqvuhHeECQH6CBr20hqyvF4CxbrHS48kXakpIHatGzkVCKeFolwwTGvMF72aPHPvDtOpOXNuQu+49VepY7ZV7V+j73TyaRdE=";

	/**
	 * 鐢熸垚瀵嗛挜瀵�(鍏挜鍜岀閽�)
	 * 
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 鐢ㄧ閽ュ淇℃伅鐢熸垚鏁板瓧绛惧悕
	 * 
	 * @param data
	 *            宸插姞瀵嗘暟鎹�
	 * @param privateKey
	 *            绉侀挜(BASE64缂栫爜)
	 * @throws Exception
	 */
	public static String sign(byte[] data) throws Exception {
		byte[] keyBytes = Base64.decode(TEST_PRIVATE_KEY.getBytes());
		//decode(TEST_PRIVATE_KEY.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return new String(Base64.encode(signature.sign()));
		//return new String(Base64.encode(signature.sign()));
	}

	/**
	 * 鏍￠獙鏁板瓧绛惧悕
	 * 
	 * @param data
	 *            宸插姞瀵嗘暟鎹�
	 * @param publicKey
	 *            鍏挜(BASE64缂栫爜)
	 * @param sign
	 *            鏁板瓧绛惧悕
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String reqSign) throws Exception {
		String sign = URLDecoder.decode(reqSign, "utf-8");
		byte[] keyBytes = Base64.decode(TEST_PUBLIC_KEY.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decode(sign.getBytes()));
	}

	/**
	 * 绉侀挜瑙ｅ瘑
	 * 
	 * @param encryptedData
	 *            宸插姞瀵嗘暟鎹�
	 * @param privateKey
	 *            绉侀挜(BASE64缂栫爜)
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 瀵规暟鎹垎娈佃В瀵�
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 鍏挜瑙ｅ瘑
	 * 
	 * @param encryptedData
	 *            宸插姞瀵嗘暟鎹�
	 * @param publicKey
	 *            鍏挜(BASE64缂栫爜)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey.getBytes());
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 瀵规暟鎹垎娈佃В瀵�
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 鍏挜鍔犲瘑
	 * 
	 * @param data
	 *            婧愭暟鎹�
	 * @param publicKey
	 *            鍏挜(BASE64缂栫爜)
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey.getBytes());
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 瀵规暟鎹姞瀵�
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 瀵规暟鎹垎娈靛姞瀵�
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 绉侀挜鍔犲瘑
	 * </p>
	 * 
	 * @param data
	 *            婧愭暟鎹�
	 * @param privateKey
	 *            绉侀挜(BASE64缂栫爜)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 瀵规暟鎹垎娈靛姞瀵�
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 鑾峰彇绉侀挜
	 * 
	 * @param keyMap
	 *            瀵嗛挜瀵�
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return new String(Base64.encode(key.getEncoded()));
	}

	/**
	 * 鑾峰彇鍏挜
	 * 
	 * @param keyMap
	 *            瀵嗛挜瀵�
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return new String(Base64.encode(key.getEncoded()));
	}
	
}