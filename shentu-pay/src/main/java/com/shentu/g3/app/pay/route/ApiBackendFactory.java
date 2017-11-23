/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.route;

import com.shentu.g3.app.pay.classloader.BackendAppClassLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shentu.g3.app.pay.backend.ApiBackend;
import com.shentu.g3.app.pay.backend.RmiBackend;
import com.shentu.g3.app.pay.classloader.BackendAppClassLoaderFactory;
import com.shentu.g3.app.pay.classloader.WFClassLoader;
import com.shentu.g3.app.pay.util.RemoteFacadeFactory;
import com.yeepay.g3.facade.whitebroad.dto.api.ApiInfoDTO;

/**
 * 
 * @ClassName: ApiBackendFactory 
 * @Description: api后台工厂
 * @author yunpeng.pan
 * @date 2017年9月19日 下午2:41:00 
 *
 */
@Component
public class ApiBackendFactory {

	private static final Log LOGGER = LogFactory.getLog(ApiBackendFactory.class);
	
    @Autowired
    private BackendAppClassLoaderFactory backendAppClassLoaderFactory;

    public ApiBackend createBackend(ApiInfoDTO apiDefine) throws Exception {
        String serviceClassUrl = apiDefine.getApiHost() + "class/";// 对应子系统servlet路径
        String endClass = apiDefine.getBackendClass();
        endClass = endClass == null ? null : endClass.trim();
        String endMethod = apiDefine.getBackendMethod();
        String backendAppName = apiDefine.getApiName();
  
        ClassLoader backendAppClassLoader = backendAppClassLoaderFactory.getClassLoader(serviceClassUrl, endClass);
        Class<?> clazz = backendAppClassLoader.loadClass(endClass);
        // 获取hessian 完整url
        String endServiceUrl = apiDefine.getApiHost() + "hessian/" + clazz.getSimpleName();
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        //set the context classLoader before create the proxy
        Thread.currentThread().setContextClassLoader(backendAppClassLoader);
        RmiBackend rmiBackend = new RmiBackend();
        try {
            rmiBackend.setEndMethod(WFClassLoader.getMethod(clazz, endMethod));
            Object service = RemoteFacadeFactory.getService(clazz, endServiceUrl, backendAppName);
            ClassLoader proxyClassLoader = service.getClass().getClassLoader();
            //如果代理的classLoader和当前backendAppClassLoader不相等（内存地址相等），说明是之前缓存的代理，需要晴空再重新获取...
            if (proxyClassLoader instanceof BackendAppClassLoader && proxyClassLoader != backendAppClassLoader) {
            	LOGGER.info("proxyClassLoader != backendAppClassLoader");
            } else {
            	LOGGER.info("proxyClassLoader == backendAppClassLoader");
            }
            rmiBackend.setProxy(service);
        } finally {
            //recover the context classLoader
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }

        return rmiBackend;
    }
}
