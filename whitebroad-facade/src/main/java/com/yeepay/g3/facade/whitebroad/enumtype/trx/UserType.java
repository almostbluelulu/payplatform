/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.whitebroad.enumtype.trx;
/**
 * @ClassName: UserType
 * @Description: UserType
 * @author: dongxulu
 * @date: 17/9/21 下午2:35
 * @version: 1.0.0
 */
import java.util.HashMap;
import java.util.Map;

public enum  UserType {
    IMEI ("IMEI","IMEI"),
    MAC ("MAC","MAC地址"),
    USER_ID ("USER_ID","用户ID" ),
    EMAIL ("EMAIL","邮箱" ),
    PHONE ("PHONE","手机号" ),
    ID_CARD ("ID_CARD","用户身份证号")  ;

    private String value;
    private String displayName;
    private static final Map<String, UserType> VALUE_MAP = new HashMap<String, UserType>();

    static {
        for (UserType item : UserType.values()) {
            VALUE_MAP.put(item.value, item);
        }
    }


    UserType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }


    public static UserType parse(String value) {
        return VALUE_MAP.get(value);
    }

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, UserType> getValueMap() {
        return VALUE_MAP;
    }
}