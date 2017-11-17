//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shentu.g3.core.whitebroad.util.security;

import java.security.MessageDigest;

public class Digest {
	public Digest() {
	}

	public static String md5Digest(String str) {
		try {
			byte[] e = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			return Hex.toHex(md.digest(e));
		} catch (Exception var3) {
			throw new RuntimeException("digest fail!", var3);
		}
	}

	public static String shaDigest(String str) {
		try {
			byte[] e = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA");
			return Hex.toHex(md.digest(e));
		} catch (Exception var3) {
			throw new RuntimeException("digest fail!", var3);
		}
	}

	public static String digest(String str, String alg, String charencoding) {
		try {
			byte[] e = str.getBytes(charencoding);
			MessageDigest md = MessageDigest.getInstance(alg);
			return Hex.toHex(md.digest(e));
		} catch (Exception var5) {
			throw new RuntimeException("digest fail!", var5);
		}
	}
}
