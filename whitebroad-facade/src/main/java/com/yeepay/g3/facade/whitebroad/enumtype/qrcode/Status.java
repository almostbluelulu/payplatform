/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.enumtype.qrcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Status
 * @Description: Status 状态枚举
 * @author: dongxulu
 * @date: 17/9/18 下午6:31
 * @version: 1.0.0
 */
public enum Status {

    ACTIVE("可用"),

    INACTIVE("不可用"),

    INIT("初始化");


    private String value;
    private String displayName;
    private static final Map<String, Status> VALUE_MAP = new HashMap<String, Status>();

    static {
        for (Status item : Status.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    Status(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static Status parse(String value) {
        return VALUE_MAP.get(value);
    }

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, Status> getValueMap() {
        return VALUE_MAP;
    }
}