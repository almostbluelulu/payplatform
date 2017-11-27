package com.shentu.g3.core.whitebroad.util;

import com.shentu.g3.core.whitebroad.util.security.AES;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 处理数据库密码
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
									 Properties props) throws BeansException {

		String jdbcUrl = props.getProperty("jdbcUrl");
		props.setProperty("jdbcUrl", jdbcUrl);

		String user = props.getProperty("user");
		props.setProperty("user", user);

		String driverClass = props.getProperty("driverClass");
		props.setProperty("driverClass", driverClass);

		String encryptPassword = props.getProperty("password");
		String password = AES.decryptFromBase64(encryptPassword, Constant.DB_AES_KEY);
		props.setProperty("password", password);

		super.processProperties(beanFactoryToProcess, props);
	}

	public static void main(String[] s) {
		System.out.print(AES.encryptToBase64("10040041077", Constant.STR_AES_KEY));
//		System.out.print(AES.decryptFromBase64("VxQzEGvoKRF7hkd0xXSH7w==", Constant.PW_AES_KEY));
	}
}
