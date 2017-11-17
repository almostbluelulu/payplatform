package com.shentu.g3.facade.whitebroad.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * 商户类型
 *
 * @author hongyu.liu
 * @date 2017年9月18日 下午3:47:26
 */
public enum CompanyType {

	COMPANY("COMPANY", "企业"),
	INDIVIDUAL("INDIVIDUAL", "个体"),
	SMALL_MICRO("SMALL_MICRO", "小微");

	private static final Map<String, CompanyType> KEY_MAP = new HashMap<String, CompanyType>();

	static {
		for (CompanyType item : CompanyType.values()) {
			KEY_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;

	CompanyType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static CompanyType parse(String key) {
		return KEY_MAP.get(key);
	}

	public static Map<String, CompanyType> getKeyMap() {
		return KEY_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}
