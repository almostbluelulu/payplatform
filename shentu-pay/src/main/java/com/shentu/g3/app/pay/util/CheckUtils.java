package com.shentu.g3.app.pay.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Description: 迁移工具
 * Author: jiawen.huang
 * Date: 2017/9/15
 * Time: 11:46
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class CheckUtils {

	public static void valueIsEmpty(String str, String checkName) {
		if (StringUtils.isBlank(str)) {
			StringBuffer sb = new StringBuffer();
			sb.append(checkName).append(" must be specified");
			throw new IllegalArgumentException(sb.toString());
		}
	}

	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message + " must be specified");
		} else if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		} else if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
	}
}
