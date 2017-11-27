/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.facade.whitebroad.enumtype.trx;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PayType
 * @Description: PayType
 * @author: dongxulu
 * @date: 17/9/18 下午4:22
 * @version: 1.0.0
 */
public enum PayType {

	NCPAY("NCPAY", "NCPAY"),
	ALIPAY("ALIPAY", "支付宝"),
	WECHAT("WECHAT", "微信"),
	CFL("CFL", "分期支付"),
	NET("NET", "网银支付"),
	JD("JD", "京东钱包"),
	SCAN_CODE("SCAN_CODE", "扫码"),
	OPEN_UPOP("OPEN_UPOP", "银联");

	private static final Map<String, PayType> VALUE_MAP = new HashMap<String, PayType>();

	static {
		for (PayType item : PayType.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	private String value;
	private String displayName;


	PayType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}


	PayType(String displayName) {
		this.displayName = displayName;
	}

	public static PayType parse(String value) {
		return VALUE_MAP.get(value);
	}

	public static Map<String, PayType> getValueMap() {
		return VALUE_MAP;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
}