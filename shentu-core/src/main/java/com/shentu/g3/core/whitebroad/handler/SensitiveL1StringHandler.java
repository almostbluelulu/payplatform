package com.shentu.g3.core.whitebroad.handler;

import com.shentu.g3.core.whitebroad.util.Constant;
import com.shentu.g3.core.whitebroad.util.security.AES;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 敏感字段(密码类)做L1级别
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 16:04
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SensitiveL1StringHandler implements TypeHandler {

	@Override
	public void setParameter(PreparedStatement preparedStatement, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null) {
			String param = (String) parameter;
			String encryptValue = StringUtils.isBlank(param) ?
					null : AES.encryptToBase64(param, Constant.PW_AES_KEY);
			preparedStatement.setString(i, encryptValue);
		} else {
			preparedStatement.setNull(i, 12);
		}
	}

	@Override
	public Object getResult(ResultSet resultSet, String s) throws SQLException {
		String toSplitStr = resultSet.getString(s);
		return convertResult(toSplitStr);
	}

	@Override
	public Object getResult(ResultSet resultSet, int i) throws SQLException {
		String toSplitStr = resultSet.getString(i);
		return convertResult(toSplitStr);
	}

	@Override
	public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
		String toSplitStr = callableStatement.getString(i);
		return convertResult(toSplitStr);
	}

	private String convertResult(String s) {
		return StringUtils.isBlank(s) ?
				null : AES.decryptFromBase64(s, Constant.PW_AES_KEY);
	}
}
