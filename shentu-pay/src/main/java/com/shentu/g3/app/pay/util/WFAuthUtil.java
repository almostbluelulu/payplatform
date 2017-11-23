package com.shentu.g3.app.pay.util;

import com.shentu.g3.app.pay.exception.WFSysException;
import com.shentu.g3.app.pay.util.security.RSA;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.shentu.g3.app.pay.context.Context;
import com.shentu.g3.app.pay.util.security.AES;
import com.shentu.g3.app.pay.util.security.Digest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description: 安全加解密工具
 * Author: jiawen.huang
 * Date: 16/4/19
 * Time: 11:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class WFAuthUtil {

	private static Log LOGGER = LogFactory.getLog(WFAuthUtil.class);

	private static final String DATA_SEPERATOR = "$";

	private static final String PATH_SEPARATOR = File.separator;

	/**
	 * 加密响应报文
	 * <p>服务端私钥加密,客户端公钥解密</p>
	 *
	 * @param source
	 * @return
	 */
	public static String encrypt(String source) {
		try {
			LOGGER.debug("加密前明文:" + source);
			byte[] randomKey = "12345678abcdefgh".getBytes();
			System.out.println(Base64.encode(randomKey));
			LOGGER.debug("AES密钥:" + randomKey);
			byte[] str1 = AES.encrypt(source.getBytes("UTF-8"), randomKey);
			LOGGER.debug("加密后密文:" + str1);
			String str1Base64 = new String(Base64.encode(str1));//加密报文
			LOGGER.debug("加密和Base64后密文:" + str1Base64);
			byte[] str2 = RSA.encrypt(randomKey, getPublicKey(Constant.rsaPublicKey));
			LOGGER.debug("加密后AES密钥:" + str2);
			String str2Base64 = new String(Base64.encode(str2));//密钥
			LOGGER.debug("加密和Base64后AES密钥:" + str2Base64);
			String str3Base64 = Digest.md5Digest(str2Base64 + DATA_SEPERATOR + str1Base64);
			LOGGER.debug("加密后签名:" + str3Base64);
			return str1Base64 + DATA_SEPERATOR + str3Base64 + DATA_SEPERATOR + str2Base64;
		} catch (UnsupportedEncodingException e) {
			throw new WFSysException("1000301", "无效编码格式");
		} catch (Exception e) {
			throw new WFSysException("1000302", "加密失败");
		}
	}
	
	/**
	 * 加密响应报文-简单加密
	 * <p>服务端私钥加密,客户端公钥解密</p>
	 *
	 * @param source
	 * @return
	 */
	public static String encryptSimple(String source, byte[] aesKey) {
		try {
			LOGGER.debug("加密前明文:" + source);
			LOGGER.debug("AES密钥:" + aesKey);
			byte[] str1 = AES.encrypt(source.getBytes("UTF-8"), aesKey);
			LOGGER.debug("加密后密文:" + str1);
			return new String(Base64.encode(str1));
		} catch (UnsupportedEncodingException e) {
			throw new WFSysException("1000301", "无效编码格式");
		} catch (Exception e) {
			e.printStackTrace();
			throw new WFSysException("1000302", "加密失败");
		}
	}

	/**
	 * 解密请求报文
	 * <p>客户端公钥加密,服务端私钥解密</p>
	 *
	 * @param source 待验签解密源串
	 * @param context 
	 * @return 解密后的业务参数串
	 */
	public static String decrypt(String source, Context context) {

		String[] args = source.split("\\" + DATA_SEPERATOR);
		if (args.length != 3) {
			throw new WFSysException("1000303", "请求报文格式错误");
		}
		String str1Base64 = args[0];//加密报文
		String str3Base64 = args[1];//签名
		String str2Base64 = args[2];//密钥
		if (StringUtils.isEmpty(str1Base64) || StringUtils.isEmpty(str2Base64)
				|| StringUtils.isEmpty(str3Base64)) {
			throw new WFSysException("1000303", "请求报文格式错误");
		}
		LOGGER.debug("解密前Base64的密文:" + str1Base64);
		LOGGER.debug("解密前Base64的AES密钥AES密钥:" + str2Base64);
		String verifySign = Digest.md5Digest(str2Base64 + DATA_SEPERATOR + str1Base64);
		if (!str3Base64.equalsIgnoreCase(verifySign)) {
			throw new WFSysException("1000304", "请求验签失败");
		} else {
			try {
				byte[] randomKey = RSA.decrypt(Base64.decode(str2Base64), getPrivateKey(Constant.rsaPrivateKey));
				byte[] sourceDate = AES.decrypt(Base64.decode(str1Base64), randomKey);
				if(context != null) {
					context.setAesKey(randomKey);
				}
				return new String(sourceDate);
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("无效编码格式:{}", e);
				throw new WFSysException("1000301", "无效编码格式");
			} catch (Exception e) {
				LOGGER.error("解密失败:{}", e);
				throw new WFSysException("1000305", "解密失败");
			}
		}
	}

	private static PublicKey getPublicKey(String pubKeyStr) throws Exception {
		X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decode(pubKeyStr));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(bobPubKeySpec);
	}

	public static PrivateKey getPrivateKey(String priKeyStr) throws Exception {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(priKeyStr));
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		return keyf.generatePrivate(priPKCS8);
	}

	private static byte[] readBytes(String prefix) throws Exception {
		InputStream inputStream = ResourceLoader.loadResource("key" + PATH_SEPARATOR + prefix + "_key.der");
		byte[] bytes = new byte[(int) inputStream.available()];
		inputStream.read(bytes);
		return bytes;
	}

	private static String getSignStr(String source) {
		return source.substring(source.indexOf("/"), source.lastIndexOf("/"));
	}

	public static void main(String[] args) throws Exception {
		String source = "{\"userNumber\":\"101\", abd}";
		String mm = WFAuthUtil.encrypt(source);
		System.out.println(mm);
		String hh = WFAuthUtil.decrypt(mm, null);
		System.out.println(hh);
		
	}
}
