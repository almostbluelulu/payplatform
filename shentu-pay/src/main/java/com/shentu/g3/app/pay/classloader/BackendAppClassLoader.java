package com.shentu.g3.app.pay.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16/11/7 上午11:43
 */
public class BackendAppClassLoader extends URLClassLoader {

    public BackendAppClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }


}