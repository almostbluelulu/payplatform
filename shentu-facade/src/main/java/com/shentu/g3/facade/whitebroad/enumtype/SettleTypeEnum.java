package com.shentu.g3.facade.whitebroad.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 结算类型
 * Author: jiawen.huang
 * Date: 2017/9/19
 * Time: 21:04
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum SettleTypeEnum {

	S0("S0", "S0"),
	T1("T1", "T1");

	private static final Map<String, SettleTypeEnum> VALUE_MAP = new HashMap<String, SettleTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (SettleTypeEnum item : SettleTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	SettleTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static SettleTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, SettleTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
