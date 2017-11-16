package com.yeepay.g3.core.whitebroad.util;

import com.yeepay.g3.utils.common.FormatUtils;

import java.util.Date;
import java.util.TimeZone;

/**
 * Description: 序列生成器
 * Author: jiawen.huang
 * Date: 2017/9/21
 * Time: 16:20
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class BizUidUtil {

	private static final int[] DEFAULT_CONFOUNDER = new int[]{6, 4, 3, 1, 2, 0, 9, 5, 8, 7};

	/**
	 * 生成唯一uuid(因为初始化序列长度问题不建议使用)
	 *
	 * @param prefixNo 前缀，例如：2
	 * @param sequence 表序列，长度要够建议11位以上
	 * @return
	 */
	@Deprecated
	public static String generateID(String prefixNo, String sequence) {
		//生成编号，以prefixNo开头
		StringBuffer bizUid = new StringBuffer(prefixNo);
		//获取数据库序列(x位）
		int length = sequence.length();
		for (int i = 0; i < length; i++) {
			int tempInt = Character.getNumericValue(sequence.charAt(i));
			tempInt += i;
			tempInt %= 10;
			//判断是否为最后一位，最后一位数字混淆
			if (i == length - 1) {
				tempInt = DEFAULT_CONFOUNDER[tempInt];
			}
			bizUid.append(tempInt);
		}
		return bizUid.toString();
	}

	/**
	 * 生成带业务前缀对uuid
	 *
	 * @param bizFlag  建议使用BizPrefixEnum，如果要用数字，请同样添加进BizPrefixEnum备案
	 * @param sequence 10位以下,sequence初始化 1 开始
	 * @return
	 */
	public static String generateBizUID(String bizFlag, long sequence) {
		String dateStr = FormatUtils.formatDate(new Date(), "yyMMdd", (TimeZone) null);
		return bizFlag + dateStr + confuse(sequence, DEFAULT_CONFOUNDER);
	}

	private static long confuse(long num, int[] confounder) {
		String tempStr = num + "";
		int length = confounder.length;
		int numLength = tempStr.length();
		if (length < numLength) {
			throw new RuntimeException("confounder length must greater then number length, " + length + " : " + numLength);
		} else {
			String output = "";
			char[] input = tempStr.toCharArray();
			int confounderIndex = Integer.parseInt(input[input.length - 1] + "") % 8;
			int paddingLength = length - numLength;

			int e;
			for (e = 0; e < paddingLength; ++e) {
				confounderIndex = (confounderIndex + 1) % 8;
				output = output + confounder[confounderIndex] % 10;
			}

			for (e = 0; e < numLength; ++e) {
				confounderIndex = (confounderIndex + 1) % 8;
				output = output + (Integer.parseInt(input[e] + "") + confounder[confounderIndex]) % 10;
			}

			try {
				return Long.parseLong(output);
			} catch (Exception var11) {
				throw new RuntimeException("confuse number overflow : " + output);
			}
		}
	}
}
