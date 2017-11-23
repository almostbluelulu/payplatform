package com.shentu.g3.app.pay.classloader;

/**
 * title: 后端应用classLoader factory<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16/11/7 上午11:42
 */
public interface BackendAppClassLoaderFactory {

    /**
     * 根据backendApp获取其对应的classLoader，如果没有，则根据endClass创建之
     *
     * @param backendApp
     * @return
     */
    ClassLoader getClassLoader(String backendApp, String endClass);

    void refreshClassLoader(String backendApp);

}
