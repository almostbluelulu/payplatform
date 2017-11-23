//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shentu.g3.app.pay.util.security;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class RSA {
	public RSA() {
	}

	public static boolean verifySign(byte[] data, byte[] sign, PublicKey publicKey) {
		try {
			Signature e = Signature.getInstance("MD5withRSA");
			e.initVerify(publicKey);
			e.update(data);
			boolean result = e.verify(sign);
			return result;
		} catch (Exception var5) {
			throw new RuntimeException("verifySign fail!", var5);
		}
	}

	public static boolean verifySign(String data, String sign, PublicKey pubicKey) {
		try {
			byte[] e = data.getBytes("UTF-8");
			byte[] signByte = Base64.decode(sign.getBytes("UTF-8"));
			return verifySign(e, signByte, pubicKey);
		} catch (UnsupportedEncodingException var5) {
			throw new RuntimeException("verifySign fail!", var5);
		}
	}

	public static byte[] sign(byte[] data, PrivateKey key) {
		try {
			Signature e = Signature.getInstance("MD5withRSA");
			e.initSign(key);
			e.update(data);
			return e.sign();
		} catch (Exception var3) {
			throw new RuntimeException("sign fail!", var3);
		}
	}

	public static String sign(String data, PrivateKey key) {
		try {
			byte[] e = data.getBytes("UTF-8");
			return new String(Base64.encode(sign(e, key)));
		} catch (UnsupportedEncodingException var3) {
			throw new RuntimeException("sign fail!", var3);
		}
	}

	public static byte[] encrypt(byte[] data, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(1, key);
			return cipher.doFinal(data);
		} catch (Exception var4) {
			throw new RuntimeException("encrypt fail!", var4);
		}
	}

	public static String encryptToBase64(String data, Key key) {
		try {
			return new String(Base64.encode(encrypt(data.getBytes("UTF-8"), key)));
		} catch (Exception var3) {
			throw new RuntimeException("encrypt fail!", var3);
		}
	}

	public static byte[] decrypt(byte[] data, Key key) {
		try {
			Cipher e = Cipher.getInstance("RSA");
			e.init(2, key);
			return e.doFinal(data);
		} catch (Exception var3) {
			throw new RuntimeException("encrypt fail!", var3);
		}
	}

	public static String decryptFromBase64(String data, Key key) {
		try {
			return new String(decrypt(Base64.decode(data.getBytes()), key), "UTF-8");
		} catch (Exception var3) {
			throw new RuntimeException("encrypt fail!", var3);
		}
	}
}
