package com.shentu.g3.facade.whitebroad.util;

import com.shentu.g3.facade.whitebroad.exception.SystemErrorCodeTranslator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Description: PropertyUtil
 * Date: 2017/9/18
 * Time: 11:06
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PropertyUtil {

	private static Map instance = Collections.synchronizedMap(new HashMap());

	private static Object lock = new Object();

	protected String sourceUrl;

	protected ResourceBundle resourceBundle;

	private static Log LOGGER = LogFactory.getLog(SystemErrorCodeTranslator.class);

	protected PropertyUtil(String sourceUrl) {
		this.sourceUrl = sourceUrl;
		load();
	}

	public static PropertyUtil getInstance(String sourceUrl) {
		synchronized (lock) {
			PropertyUtil manager = (PropertyUtil) instance.get(sourceUrl);
			if (manager == null) {
				manager = new PropertyUtil(sourceUrl);
				instance.put(sourceUrl, manager);
			}
			return manager;
		}
	}

	private synchronized void load() {
		try {
			resourceBundle = ResourceBundle.getBundle(sourceUrl);
		} catch (Exception e) {
			LOGGER.error("sourceUrl = " + sourceUrl + " file load error!", e);
			throw new RuntimeException(e);
		}
	}

	public synchronized String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
