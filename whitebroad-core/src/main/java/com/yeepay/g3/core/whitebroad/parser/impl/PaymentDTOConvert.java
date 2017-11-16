package com.yeepay.g3.core.whitebroad.parser.impl;

import com.yeepay.g3.core.whitebroad.entity.trade.Payment;
import com.yeepay.g3.core.whitebroad.parser.DTOConvert;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.dto.trade.PaymentDTO;
import org.springframework.beans.BeanUtils;

/**
 * Description: payment2dto
 * Author: jiawen.huang
 * Date: 2017/10/23
 * Time: 10:48
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PaymentDTOConvert implements DTOConvert<PaymentDTO, Payment> {

	@Override
	public Payment convert2Entity(PaymentDTO d) {
		return null;
	}

	@Override
	public PaymentDTO convert2DTO(Payment entity) {
		PaymentDTO paymentDTO = new PaymentDTO();
		if (entity != null) {
			BeanUtils.copyProperties(entity, paymentDTO);
			paymentDTO.setOrderId(entity.getOrder_id());
			paymentDTO.setPayType(entity.getPayType() != null ? entity.getPayType().getValue() : null);
			paymentDTO.setCardType(entity.getCardType() != null ? entity.getCardType().name() : null);
			paymentDTO.setTrxType(entity.getTrxType() != null ? entity.getTrxType().name() : null);
			paymentDTO.setPayStatus(entity.getPayStatus() != null ? entity.getPayStatus().name() : null);
			paymentDTO.setTrxAmt(entity.getTrxAmt() != null ? entity.getTrxAmt().toPlainString() : null);
			paymentDTO.setRealAmount(entity.getRealAmount() != null ? entity.getRealAmount().toPlainString() : null);
			paymentDTO.setFeeAmount(entity.getFeeAmount() != null ? entity.getFeeAmount().toPlainString() : null);
			paymentDTO.setCreateTime(DateUtils.toString(entity.getCreateTime(), DateUtils.DATE_FORMAT_DATETIME));
			paymentDTO.setCompleteTime(DateUtils.toString(entity.getCompleteTime(), DateUtils.DATE_FORMAT_DATETIME));
		}
		return paymentDTO;
	}
}