package com.yeepay.g3.facade.whitebroad.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: app版本枚举类
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:33
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum AppRoleEnum {

	WB_DEMO("WB_DEMO", "白板demo版");

	private static final Map<String, AppRoleEnum> VALUE_MAP = new HashMap<String, AppRoleEnum>();

	private String value;
	private String displayName;

	static {
		for (AppRoleEnum item : AppRoleEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	AppRoleEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static AppRoleEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, AppRoleEnum> getValueMap() {
		return VALUE_MAP;
	}
}
