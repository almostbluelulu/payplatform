package com.shentu.g3.core.whitebroad.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName: AmountUtil
 * @Description: 金额处理工具类
 * @author: dongxulu
 * @date: 17/9/20 下午5:18
 * @version: 1.0.0
 */
public class AmountUtil {
	
	/**
	 * long型转成BigDecimal的带小数点金额
	 */
	public static BigDecimal formatLongAmout(long amt) {
		BigDecimal bd = new BigDecimal(amt/100.00);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}

	/**
	 * double型转成BigDecimal的带小数点金额
	 */
	public static BigDecimal formatDoubleAmout(Double amt) {
		if (amt == null) {
			amt = 0d;
		}
		BigDecimal bd = new BigDecimal(amt/1.00);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd;
	}

	/**
	 * String 转Bigdecimal
	 */
	public static BigDecimal formatStringAmount(String amount){
		if(null == amount) {
			return new BigDecimal(0.00);
		}
		double amountl = Double.valueOf(amount);
		BigDecimal amt = BigDecimal.valueOf(amountl);
		amt = amt.setScale(2, RoundingMode.HALF_UP);
		return amt;
	}


	/**
	 * 交易金额减去费用
	 * @param trxAmt
	 * @param fee
	 * @return
	 */
	public static BigDecimal getSubstractFeeOrderAmount(BigDecimal trxAmt,BigDecimal fee){

		if (trxAmt == null) {
			return null;
		}



		return trxAmt.subtract(fee);
	}


}
