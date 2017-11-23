package com.shentu.g3.app.pay.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 支持的响应的格式类型
 */
public enum FormatType {

	xml, json, stream;

	public static FormatType getFormat(String value) {
		if (StringUtils.isBlank(value)) {
			return json;
		} else {
			try {
				return FormatType.valueOf(value.toLowerCase());
			} catch (IllegalArgumentException e) {
				return json;
			}
		}
	}

	public static boolean isValidFormat(String value) {
		if (StringUtils.isBlank(value)) {
			return true;
		} else {
			try {
				FormatType.valueOf(value.toLowerCase());
				return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
	}
}