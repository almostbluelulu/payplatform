package com.shentu.g3.core.whitebroad.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: 打印YOP日志
 * Author: wei.li
 * Date: 17/10/19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class YOPLoggerUtil {

    private static final List<String> IGNORE_FIELD = Arrays.asList("image_base64");

    public static String toQueryString(Map paramMap) {
        StringBuilder builder = new StringBuilder();
        Iterator iterator = paramMap.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            List values = (List) paramMap.get(key);
            Iterator iterator1 = values.iterator();
            if (!IGNORE_FIELD.contains(key)) {
                while (iterator1.hasNext()) {
                    String value = (String) iterator1.next();
                    builder.append(builder.length() == 0 ? "" : "&");
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                }
            }
        }

        return builder.toString();
    }
}
