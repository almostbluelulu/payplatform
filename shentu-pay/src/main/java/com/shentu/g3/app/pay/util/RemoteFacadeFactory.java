package com.shentu.g3.app.pay.util;


import java.lang.reflect.Proxy;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;
import com.shentu.g3.app.pay.enums.ExternalSystemUrl;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;

/**
 * Description: 远程接口初始化类
 * Author: jiawen.huang
 * Date: 16/9/19
 * Time: 15:24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class RemoteFacadeFactory {

	/**
	 * 获取和代理非三代发布的hessian服务
	 *
	 * @param targetClass
	 * @param externalName
	 * @param <T>
	 * @return
	 * @throws MalformedURLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> targetClass, ExternalSystemUrl externalName) {
		HessianProxyFactory factory = new HessianProxyFactory();
		String serviceName = externalName.getServiceName();
		String serviceUrl = externalName.getServiceURL();
		try {
			Object target = factory.create(targetClass, serviceUrl);
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, serviceName);
			return (T) Proxy.newProxyInstance(logHandler.getClass().getClassLoader(), target.getClass().getInterfaces(), logHandler);
		} catch (MalformedURLException e) {
			throw new WbSysException(ErrorCode.HESSIAN_CONNECT_EXCEPTION, e);
		}
	}
	
	/**
	 * 获取远程发布的hessian服务
	 *
	 * @param targetClass
	 * @param <T>
	 * @return
	 * @throws MalformedURLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> targetClass, String serviceUrl, String serviceName) {
		HessianProxyFactory factory = new HessianProxyFactory(targetClass.getClassLoader());
		try {
			Object target = factory.create(targetClass, serviceUrl);
			RemoteFacadeLogHandler logHandler = new RemoteFacadeLogHandler(target, serviceName);
			return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), 
					new Class[] { targetClass, HessianRemoteObject.class }, logHandler);
		} catch (MalformedURLException e) {
			throw new WbSysException(ErrorCode.HESSIAN_CONNECT_EXCEPTION, e);
		}
	}

}