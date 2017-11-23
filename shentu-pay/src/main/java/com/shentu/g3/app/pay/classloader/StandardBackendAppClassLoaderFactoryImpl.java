package com.shentu.g3.app.pay.classloader;


import java.net.URL;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: StandardBackendAppClassLoaderFactoryImpl 
 * @Description: api后台工厂实现
 * @author yunpeng.pan
 * @date 2017年9月19日 下午3:24:15 
 *
 */
@Component
public class StandardBackendAppClassLoaderFactoryImpl implements BackendAppClassLoaderFactory {

	private static final Log LOGGER = LogFactory.getLog(StandardBackendAppClassLoaderFactoryImpl.class);
	
    private Map<String, BackendAppClassLoader> classLoaderRegistry = Maps.newHashMap();

//    private WbApiInfoFacade wbApiInfoFacade = RemoteFacadeFactory.getService(WbApiInfoFacade.class, ExternalSystem.WHITEBROAD);

    @Override
    public synchronized ClassLoader getClassLoader(String endServiceUrl, String endClass) {
        if (StringUtils.isBlank(endServiceUrl)) {
            return Thread.currentThread().getContextClassLoader();
        }
        LOGGER.info("get backendApp classLoader,endServiceUrl:"+ endServiceUrl +",endClass:"+ endClass);
        if (classLoaderRegistry.containsKey(endServiceUrl)) {
            LOGGER.info("classLoader already exists,endServiceUrl:"+ endServiceUrl +",endClass:"+ endClass);
            return classLoaderRegistry.get(endServiceUrl);
        }

        LOGGER.info("create backendApp URLClassLoader,endServiceUrl:"+ endServiceUrl +",endClass:"+ endClass);
        try {
            URL[] urls = new URL[1];
            urls[0] = new URL(endServiceUrl);
            BackendAppClassLoader backendAppClassLoader = new BackendAppClassLoader(urls, Thread.currentThread().getContextClassLoader());
            classLoaderRegistry.put(endServiceUrl, backendAppClassLoader);
            return backendAppClassLoader;
        } catch (Exception e) {
            LOGGER.error("error when create classLoader", e);
            //if exception happens when creating classLoader,simply return the thread context classLoader.
            return Thread.currentThread().getContextClassLoader();
        }
    }

    @Override
    public synchronized void refreshClassLoader(String endServiceUrl) {
        LOGGER.info("refresh classLoader,endServiceUrl:{}"+ endServiceUrl);
        BackendAppClassLoader old = classLoaderRegistry.get(endServiceUrl);
        if (old == null) {
            return;
        }
        //new instance is absolutely same as the old one
        classLoaderRegistry.put(endServiceUrl, new BackendAppClassLoader(old.getURLs(), old.getParent()));
    }
}
