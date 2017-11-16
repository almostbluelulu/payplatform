package com.yeepay.g3.facade.whitebroad.enumtype.trx;

import java.util.HashMap;
import java.util.Map;

/**
 * 开户状态
 *
 * @author hongyu.liu
 * @date 2017年9月19日 上午9:53:10
 */
public enum AccountOpenStatus {

	INIT("INIT", "初始化"),
	AUDITING("AUDITING", "审核中"),
	RETURN("RETURN", "退回"),
	REJECT("REJECT", "拒绝"),
	SUCCESS("SUCCESS", "成功");

	private static final Map<String, AccountOpenStatus> VALUE_MAP = new HashMap<String, AccountOpenStatus>();

	private String value;
	private String displayName;

	static {
		for (AccountOpenStatus item : AccountOpenStatus.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	AccountOpenStatus(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static AccountOpenStatus parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, AccountOpenStatus> getValueMap() {
		return VALUE_MAP;
	}
}
