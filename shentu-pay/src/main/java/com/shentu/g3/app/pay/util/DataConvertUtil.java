package com.shentu.g3.app.pay.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.shentu.g3.app.pay.exception.WFSysException;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/4/25
 * Time: 17:27
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class DataConvertUtil {
	/**
	 * 防止=被Unicode
	 */
	public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public static String toJson(Object object) {
		return gson.toJson(object).toString();
	}

	public static <T> T fromJson(String json, Class<T> classT) {
		try {
			return gson.fromJson(json, classT);
		} catch (JsonSyntaxException e) {
			throw new WFSysException("", "", e);
		}
	}
}
