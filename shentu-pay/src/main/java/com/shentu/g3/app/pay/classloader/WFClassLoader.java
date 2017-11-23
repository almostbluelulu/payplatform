/**
 *
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 *
 */
package com.shentu.g3.app.pay.classloader;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;

import com.shentu.g3.app.pay.context.Context;

/**
 * 远程端点API类加载器<br>
 * 若api平台本地不存在类，支持动态创建<br>
 * 一个类多个方法,已加载的类还需再暴露一个方法，添加配置后需重启api平台应用才可使用
 *
 * @author：wang.bao
 * @since：2015年3月4日 上午11:20:21
 * @version:
 */
public class WFClassLoader {

    /**
     * 参数类型缩写
     */
    private static final ConcurrentMap<String, Class<?>> shortParamTypes = new ConcurrentHashMap<String, Class<?>>();

    private static final String SPLITTER = ",";


    /**
     * 判断方法是否带参数
     *
     * @param methodName
     * @return
     */
    private static boolean isNoneParamMethod(String methodName) {
        int start = methodName.indexOf("(");
        int end = methodName.indexOf(")");
        return start == -1 && end == -1 || end - start == 1
                || methodName.substring(start + 1, end).trim().length() == 0;
    }

    /**
     * 从已加载类中获取方法定义
     *
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class<?> clazz, String methodDetail)
            throws ClassNotFoundException, NoSuchMethodException {
        MethodDefine methodDefine = new MethodDefine(methodDetail);
        return clazz.getMethod(methodDefine.getMethodName(), methodDefine.getParamTypes());
    }

    /**
     * 描述： 获得方法参数类型
     *
     * @param classNames
     * @return
     * @throws ClassNotFoundException
     */
    private static Class<?>[] getParamsClass(String[] classNames)
            throws ClassNotFoundException {
        if (classNames == null || classNames.length < 1) {
            return null;
        }
        Class<?>[] paramsClass = new Class[classNames.length];
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        for (int i = 0; i < classNames.length; i++) {
            classNames[i] = StringUtils.trimToEmpty(classNames[i]);
            paramsClass[i] = findParamType(classNames[i]);
            if (paramsClass[i] == null) {
                paramsClass[i] = classLoader.loadClass(classNames[i]);
            }
        }
        return paramsClass;
    }

    static {
        shortParamTypes.put("S", String.class);
        shortParamTypes.put("b", Byte.class);
        shortParamTypes.put("s", Short.class);
        shortParamTypes.put("i", Integer.class);
        shortParamTypes.put("l", Long.class);
        shortParamTypes.put("f", Float.class);
        shortParamTypes.put("d", Double.class);
        shortParamTypes.put("dt", Date.class);
        shortParamTypes.put("ctx", Context.class);

        Class<?>[] classes = {String.class, Short.class, Integer.class,
                Long.class, Double.class, Float.class, Byte.class, List.class,
                Map.class, Set.class, Date.class, Iterator.class,
                Context.class};
        for (Class<?> clazz : classes) {
            addShortParamType(clazz);
        }
    }

    private static void addShortParamType(Class<?> _class) {
        shortParamTypes.put(_class.getSimpleName().toLowerCase(), _class);
        shortParamTypes.put(_class.getName(), _class);
    }

    public static Class<?> findParamType(String typeName) {
        Class<?> clazz = shortParamTypes.get(typeName);
        if (clazz == null && !"S".equalsIgnoreCase(typeName)) {
            clazz = shortParamTypes.get(typeName.toLowerCase());
        }
        return clazz;
    }

    public static String toShortType(Class<?> type) {
        String shorted = type.getName();
        for (Entry<String, Class<?>> shortType : shortParamTypes.entrySet()) {
            if (shortType.getValue() == type
                    && shortType.getKey().length() < shorted.length()) {
                shorted = shortType.getKey();
            }
        }
        return shorted;
    }

    private static class MethodDefine {
        private String methodName;

        private String[] paramTypeNames;

        private Class<?>[] paramTypes;

        private String returnTypeName;

        public MethodDefine(String methodDetail) throws ClassNotFoundException {
            methodDetail = methodDetail.trim();
            String m = methodDetail.substring(0, methodDetail.indexOf("("));
            int p = m.indexOf(" ");
            if (p > 0) {
                // 指定返回值的场景，e.g int getManager(java.lang.String);
                returnTypeName = m.substring(0, m.indexOf(" "));
                if (returnTypeName.equalsIgnoreCase("void")) {
                    // e.g void setValue(java.lang.String)
                    returnTypeName = null;
                }
                methodName = m.substring(p + 1).trim();
            } else {
                // 为指定返回值，默认为void
                methodName = m;
            }
            // 无参
            if (isNoneParamMethod(methodDetail)) {
                paramTypes = new Class<?>[0];
            } else {
                // 带参数
                paramTypeNames = methodDetail.substring(
                        methodDetail.indexOf("(") + 1,
                        methodDetail.indexOf(")")).split(SPLITTER);
                paramTypes = getParamsClass(paramTypeNames);
            }
        }

        public String getMethodName() {
            return methodName;
        }

        public String[] getParamTypeNames() {
            return paramTypeNames;
        }

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }

        public String getReturnTypeName() {
            return returnTypeName;
        }
    }
}
