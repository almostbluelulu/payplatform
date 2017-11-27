package com.shentu.g3.facade.whitebroad.enumtype;

import java.util.HashMap;
import java.util.Map;

public enum ExternalSystem {

	NOTIFY("notifier", "/rest/v2.0/notifier/send", EncryptType.AES),//短信通知api
    SETTLE("settle", "/rest/v1.0/whitebroad/pay/list", EncryptType.AES),//settle api
    OPR_SYS("opr-hessian", "/rest/v1.0/sys/trade/order", EncryptType.RSA),//订单处理器系统商版下单uri
	OPR_STD("opr-hessian", "/rest/v1.0/std/trade/order", EncryptType.RSA),//订单处理器下单uri
	UPLOAD("UPLOAD", "/rest/v1.0/whitebroad/file/upload", EncryptType.AES),//图片上传
	/**
	 * 入网
	 */
	// QA
//	APPLY_SMALL_MICRO("APPLY_SMALL_MICRO", "/rest/v1.0/mer/ds/reginfoadd", EncryptType.AES),
//	APPLY_INDIVIDUAL("APPLY_INDIVIDUAL", "/rest/v1.0/mer/ds/individualreginfoadd", EncryptType.AES),
//	APPLY_COMPANY("APPLY_COMPANY", "/rest/v1.0/mer/ds/enterprisereginfoadd", EncryptType.AES),
    //查询子商户秘钥uri
//    QRUERY_MERCHANT_KEY("QRUERY_MERCHANT_KEY", "/rest/v1.0/mer/hmackeyquery", EncryptType.RSA),
	QRUERY_MERCHANT_KEY("QRUERY_MERCHANT_KEY", "/rest/v1.0/sys/merchant/hmackeyquery", EncryptType.RSA),

    // 生产
    APPLY_SMALL_MICRO("APPLY_SMALL_MICRO", "/rest/v1.0/sys/merchant/personreginfoadd", EncryptType.AES),
    APPLY_INDIVIDUAL("APPLY_INDIVIDUAL", "/rest/v1.0/sys/merchant/individualreginfoadd", EncryptType.AES),
    APPLY_COMPANY("APPLY_COMPANY", "/rest/v1.0/sys/merchant/enterprisereginfoadd", EncryptType.AES);

	private static final Map<String, ExternalSystem> KEY_MAP = new HashMap<String, ExternalSystem>();

	static {
		for (ExternalSystem item : ExternalSystem.values()) {
			KEY_MAP.put(item.serviceName, item);
		}
	}

	private String serviceName;

	private String serviceURL;

	private EncryptType encryptType;

	ExternalSystem(String serviceName, String serviceURL, EncryptType encryptType) {
		this.serviceName = serviceName;
		this.serviceURL = serviceURL;
		this.encryptType = encryptType;
	}

	public static ExternalSystem parse(String key) {
		return KEY_MAP.get(key);
	}

	public static Map<String, ExternalSystem> getKeyMap() {
		return KEY_MAP;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public EncryptType getEncryptType() {
		return encryptType;
	}

	public enum EncryptType {
		AES("AES"),
		RSA("RSA");

		private final String description;

		EncryptType(String description) {
			this.description = description;
		}
	}
}
