package com.shentu.g3.core.whitebroad.util;


import com.caucho.hessian.client.HessianProxyFactory;
import com.google.common.collect.Maps;
import com.shentu.g3.core.whitebroad.aop.RemoteFacadeLogHandler;
import com.shentu.g3.facade.whitebroad.enumtype.ExternalSystem;
import com.shentu.g3.facade.whitebroad.exception.ErrorCode;
import com.shentu.g3.facade.whitebroad.exception.WbSysException;
import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Description: 远程接口初始化类
 * Author: jiawen.huang
 * Date: 16/9/19
 * Time: 15:24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class RemoteFacadeFactory {

	private static Log LOGGER = LogFactory.getLog(RemoteFacadeFactory.class);

	/**
	 * 获取和代理非三代发布的hessian服务
	 *
	 * @param targetClass
	 * @param externalName
	 * @param <T>
	 * @return
	 * @throws MalformedURLException
	 */
	public static <T> T getServiceBy2G(Class<T> targetClass, ExternalSystem externalName) {
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
	 * 调用易宝服务
	 *
	 * @param externalName
	 * @param params
	 * @return 业务结果
	 * @throws WbSysException 与yop连接等非业务异常时抛出
	 */
	public static Object getYopService(ExternalSystem externalName, Map<String, Object> params) {
		long startTime = System.currentTimeMillis();
		String secretKey = externalName.getEncryptType().equals(ExternalSystem.EncryptType.AES)
				? Constant.YOP_APP_AES_SECRET : Constant.YOP_APP_RSA_SECRET;
		YopRequest request = new YopRequest(Constant.YOP_APP_KEY, secretKey, Constant.YOP_HOST);
//		request.setEncrypt(true);
		request.setSignRet(true);
		request.setSignAlg("SHA-256");
		for (String key : params.keySet()) {
			request.addParam(key, params.get(key));
		}
		LOGGER.info("[remote_sys] - [入参] - [" + externalName.getServiceName() + "] - [ getYopService ] - [" + YOPLoggerUtil.toQueryString(request.getParams()) + "]");
		try {
			YopResponse response = externalName.getEncryptType().equals(ExternalSystem.EncryptType.RSA)
					? YopClient3.postRsa(externalName.getServiceURL(), request): YopClient.post(externalName.getServiceURL(), request);

			LOGGER.info("[remote_sys] - [返回] - [" + externalName.getServiceName() + "] - [ getYopService ] - " + response.toString());
			if (!response.getState().equals("SUCCESS")) {
				throw new WbSysException(response.getError().getCode(), response.getError().getMessage());
			}
			return response.getResult();
		} catch (WbSysException e) {
			throw e;
		} catch (Throwable e) {
			LOGGER.warn("[remote_sys] - [异常] - [" + externalName.getServiceName() + "] - [ getYopService ]", e);
			throw new WbSysException(ErrorCode.YOP_EXCEPTION, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[remote_sys] - [时间] - [" + externalName.getServiceName() + "] - [ getYopService ] - 耗时[" + time
					+ "]毫秒");
		}
	}

	/**
	 * 通过http调用的服务
	 *
	 * @param externalName
	 * @param querys
	 * @param headers
	 * @param bodys
	 * @return
	 */
	public static String getHttpPostService(ExternalSystem externalName, Map<String, String> querys,
											Map<String, String> headers, Map<String, String> bodys) {
		long startTime = System.currentTimeMillis();
		try {
			LOGGER.info("[remote_sys] - [入参] - [" + externalName.getServiceName() + "] - [ getHttpPostService ] - "
					+ querys + headers + bodys);
			if (null == headers) {
				headers = Maps.newHashMap();
			}
			if (null == bodys) {
				bodys = Maps.newHashMap();
			}
			CheckUtils.notEmpty(querys, "querys");//IllegalArgumentException
			String URL = externalName.getServiceURL();
			String response = HttpUtils.post(URL, "", headers, querys, bodys);
			LOGGER.info("[remote_sys] - [返回] - [" + externalName.getServiceName() + "] - [ getHttpPostService ] - "
					+ response);
			return response;
		} catch (Throwable e) {
			LOGGER.warn("[remote_sys] - [异常] - [" + externalName.getServiceName() + "] - [ getHttpPostService ]", e);
			throw new WbSysException(ErrorCode.SYSTEM_EXCEPTION, e);
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[remote_sys] - [时间] - [" + externalName.getServiceName() + "] - [ getHttpPostService ] - 耗时[" + time
					+ "]毫秒");
		}
	}
}
