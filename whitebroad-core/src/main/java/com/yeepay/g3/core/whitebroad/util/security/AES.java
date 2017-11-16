
package com.yeepay.g3.core.whitebroad.util.security;


import com.yeepay.g3.core.whitebroad.util.CheckUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES {
	public AES() {
	}

	public static byte[] encrypt(byte[] data, byte[] key) {
		CheckUtils.notEmpty(data, "data");
		CheckUtils.notEmpty(key, "key");
		if (key.length != 16) {
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		} else {
			try {
				SecretKeySpec e = new SecretKeySpec(key, "AES");
				byte[] enCodeFormat = e.getEncoded();
				SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(1, seckey);
				byte[] result = cipher.doFinal(data);
				return result;
			} catch (Exception var7) {
				throw new RuntimeException("encrypt fail!", var7);
			}
		}
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		CheckUtils.notEmpty(data, "data");
		CheckUtils.notEmpty(key, "key");
		if (key.length != 16) {
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		} else {
			try {
				SecretKeySpec e = new SecretKeySpec(key, "AES");
				byte[] enCodeFormat = e.getEncoded();
				SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(2, seckey);
				byte[] result = cipher.doFinal(data);
				return result;
			} catch (Exception var7) {
				throw new RuntimeException("decrypt fail!", var7);
			}
		}
	}

	public static String encryptToBase64(String data, String key) {
		try {
			byte[] e = encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
			return new String(Base64.encode(e));
		} catch (UnsupportedEncodingException var3) {
			throw new RuntimeException("encrypt fail!", var3);
		}
	}

	public static String decryptFromBase64(String data, String key) {
		try {
			byte[] e = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(e, key.getBytes("UTF-8"));
			return new String(valueByte, "UTF-8");
		} catch (UnsupportedEncodingException var4) {
			throw new RuntimeException("decrypt fail!", var4);
		}
	}

	public static String encryptWithKeyBase64(String data, String key) {
		try {
			byte[] e = encrypt(data.getBytes("UTF-8"), Base64.decode(key.getBytes()));
			return new String(Base64.encode(e));
		} catch (UnsupportedEncodingException var3) {
			throw new RuntimeException("encrypt fail!", var3);
		}
	}

	public static String decryptWithKeyBase64(String data, String key) {
		try {
			byte[] e = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(e, Base64.decode(key.getBytes()));
			return new String(valueByte, "UTF-8");
		} catch (UnsupportedEncodingException var4) {
			throw new RuntimeException("decrypt fail!", var4);
		}
	}

	public static byte[] genarateRandomKey() {
		KeyGenerator keygen = null;

		try {
			keygen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException var3) {
			throw new RuntimeException(" genarateRandomKey fail!", var3);
		}

		SecureRandom random = new SecureRandom();
		keygen.init(random);
		SecretKey key = keygen.generateKey();
		return key.getEncoded();
	}

	public static String genarateRandomKeyWithBase64() {
		return new String(Base64.encode(genarateRandomKey()));
	}
}
