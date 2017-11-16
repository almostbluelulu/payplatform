/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.whitebroad.biz.impl;

import com.google.gson.Gson;
import com.yeepay.g3.core.whitebroad.biz.AbstractBiz;
import com.yeepay.g3.core.whitebroad.biz.OprServiceBiz;
import com.yeepay.g3.core.whitebroad.biz.TradeServiceBiz;
import com.yeepay.g3.core.whitebroad.entity.AccountOpenEntity;
import com.yeepay.g3.core.whitebroad.entity.UserEntity;
import com.yeepay.g3.core.whitebroad.entity.qrcode.QrCode;
import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import com.yeepay.g3.core.whitebroad.entity.trade.Payment;
import com.yeepay.g3.core.whitebroad.parser.impl.OrderDTOConvert;
import com.yeepay.g3.core.whitebroad.task.AsyncPushTask;
import com.yeepay.g3.core.whitebroad.util.AmountUtil;
import com.yeepay.g3.core.whitebroad.util.BizUidUtil;
import com.yeepay.g3.core.whitebroad.util.Constant;
import com.yeepay.g3.core.whitebroad.util.security.AES;
import com.yeepay.g3.facade.whitebroad.dto.opr.*;
import com.yeepay.g3.facade.whitebroad.dto.qrCode.QrCodeRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderInfoDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderRequestDTO;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderResponseDTO;
import com.yeepay.g3.facade.whitebroad.enumtype.BizPrefixEnum;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.OrderStatus;
import com.yeepay.g3.facade.whitebroad.enumtype.trx.TrxType;
import com.yeepay.g3.facade.whitebroad.exception.ErrorCode;
import com.yeepay.g3.facade.whitebroad.exception.WbSysException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrderServiceBizImpl
 * @Description: OrderServiceBizImpl
 * @author: dongxulu
 * @date: 17//9 上午11:27
 * @version: 1.0.0
 */
@Service
public class TradeServiceBizImpl extends AbstractBiz implements TradeServiceBiz {
	@Autowired
	private OprServiceBiz oprServiceBiz;
	@Autowired
	private AsyncPushTask asyncPushTask;

	@Override
	public GetCashierUrlResponseDTO getCashierUrl(GetCashierUrlRequestDTO requestDTO) {
		GetCashierUrlResponseDTO responseDTO = new GetCashierUrlResponseDTO();
		String orderId = requestDTO.getOrderId().replace(" ", "+");
		orderId = AES.decryptFromBase64(orderId, Constant.STR_AES_KEY);
		Order order = orderService.findbyID(orderId);
		if (null == order) {
			throw new WbSysException(ErrorCode.ORDER_UNKNOW_EXCEPTION);
		}
		String payurl = oprServiceBiz.getPayUrl(order);
		responseDTO.setPayUrl(payurl);
		return responseDTO;
	}

	@Override
	public TrxResponseDTO createOrder(TrxRequestDTO requestDTO) throws Throwable {
		TrxResponseDTO oprResponseDTO = new TrxResponseDTO();
		Order order;
		Payment payment;
		OprOrderParamResponseDTO responseDTO;
		AccountOpenEntity accountOpenEntity;
		try {
			userService.findRegisterByUserNo(requestDTO.getUserNumber());
			accountOpenEntity = applyService.getPayableByUserNumber(requestDTO.getUserNumber());
			Long sequence = orderService.getSequnce();
			requestDTO.setMerchantOrderId(BizUidUtil.generateBizUID(BizPrefixEnum.WB_TRX.getValue(), sequence));
			requestDTO.setParentMerchantNo(Constant.MERCHANT_NUMBER);
			requestDTO.setMerchantNo(accountOpenEntity.getCustomerNo());
			requestDTO.setOutCustomerNo(accountOpenEntity.getYpCustomerNumber());
			OprOrderParamRequestDTO paramRequestDTO = initOprParam(requestDTO, accountOpenEntity);
			responseDTO = oprServiceBiz.creatOprOrder(paramRequestDTO);
			Order isorder = orderService.findbyRequestNo(requestDTO.getRequestNo(), requestDTO.getMerchantNo());
			//存储订单token 方便短链使用
			if (null != isorder) {
				order = isorder;
			} else {
				order = initOrder(requestDTO);
				order.setToken(responseDTO.getToken());
				order.setYeepayOrderId(responseDTO.getUniqueOrderNo());
				order = orderService.insert(order);
				payment = initPayment(requestDTO);
				payment.setOrder_id(order.getId());
				payment.setYeepayOrderId(responseDTO.getUniqueOrderNo());
				payment.setTrxType(TrxType.PURCHASE);
				paymentService.insert(payment);
			}
		} catch (Throwable throwable) {
			throw throwable;
		}
		oprResponseDTO = initOprResponse(order);
		return oprResponseDTO;
	}

	@Override
	public TrxResponseDTO deskQrPay(QrCodeRequestDTO requestDTO) throws Throwable {
		TrxResponseDTO oprResponseDTO = new TrxResponseDTO();
		String qrid = requestDTO.getQrId();
		QrCode qrCode = qrCodeService.getQrCodeByID(qrid);
		qrCode.getUserNumber();

		return null;
	}


	@Override
	public OprCallBackResponseParam payCallBackManager(OprPayCallBackRequestParam param) {
		OprCallBackResponseParam responseParam = new OprCallBackResponseParam();
		Order order = orderService.findByYeepayId(param.getUniqueOrderNo());
		if (OrderStatus.SUCCESS.equals(order.getOrderStatus())) {
			return responseParam;
		}
		order.setOrderStatus(OrderStatus.SUCCESS);
		order.setRealAmount(AmountUtil.formatStringAmount(param.getPayAmount()));
		orderService.update(order);
		Payment payment = paymentService.selectByOrderID(order.getId(), TrxType.PURCHASE);
		payment.setPayStatus(OrderStatus.SUCCESS);
		payment.setRealAmount(AmountUtil.formatStringAmount(param.getPayAmount()));
		paymentService.update(payment);
//      推送支付信息
		asyncPushTask.pushPayMsg2APP(payment);
		return responseParam;
	}

	@Override
	public OprCallBackResponseParam csCallBackManager(OprCSCallBackRequestParam param) {
		OprCallBackResponseParam responseParam = new OprCallBackResponseParam();
		String yeepayOrderId = param.getUniqueOrderNo();
		String customerNumber = param.getMerchantNo();
		Payment payment = paymentService.selectByYeepayOrderID(yeepayOrderId);
		if (OrderStatus.SETTELED.equals(payment.getPayStatus())) {
			return responseParam;
		}
		payment.setPayStatus(OrderStatus.SETTELED);
		//默认都是商户手续费 后期如有修改,此处要根据商户手续费类型判定取值
		payment.setFeeAmount(AmountUtil.formatStringAmount(param.getMerchantFee()));
		paymentService.update(payment);
		return responseParam;
	}

	private TrxResponseDTO initOprResponse(Order order) {
		TrxResponseDTO oprResponseDTO = new TrxResponseDTO();
		oprResponseDTO.setOrderId(order.getId());
		oprResponseDTO.setMerchantOrderId(order.getRequestNo());
		// 此处url写入的是短链地址
		oprResponseDTO.setPayUrl(Constant.SHORTLINK_HOST + AES.encryptToBase64(order.getId(), Constant.STR_AES_KEY));
		return oprResponseDTO;
	}

	private Order initOrder(TrxRequestDTO requestDTO) {
		Order order = new Order();
		order.setUserNumber(requestDTO.getUserNumber());
		order.setTrxAmt(AmountUtil.formatStringAmount(requestDTO.getOrderAmount()));
		order.setRequestNo(requestDTO.getMerchantOrderId());
		order.setCustomerNumber(requestDTO.getMerchantNo());
		order.setPayType(requestDTO.getPayType());
		order.setScanType(requestDTO.getScanType());
		order.setQrID(requestDTO.getQrId());
		return order;
	}

	private Payment initPayment(TrxRequestDTO requestDTO) {
		Payment payment = new Payment();
		payment.setTrxAmt(AmountUtil.formatStringAmount(requestDTO.getOrderAmount()));
		payment.setPayType(requestDTO.getPayType());
		payment.setCustomerNumber(requestDTO.getMerchantNo());
		payment.setPayStatus(OrderStatus.PROCESS);
		return payment;
	}

	private OprOrderParamRequestDTO initOprParam(TrxRequestDTO requestDTO, AccountOpenEntity accountOpenEntity) {
		Map<String, String> goodsMap = new HashMap<String, String>();
		if (!StringUtils.isEmpty(requestDTO.getGoodsName())) {
			goodsMap.put("goodsName", requestDTO.getGoodsName());
		} else {
			goodsMap.put("goodsName", accountOpenEntity.getMerShortName() + "收款");
		}
		if (!StringUtils.isEmpty(requestDTO.getGoodsDesc())) {
			goodsMap.put("goodsDesc", requestDTO.getGoodsDesc());
		} else {
			goodsMap.put("goodsDesc", accountOpenEntity.getMerShortName() + "收款");
		}
		OprOrderParamRequestDTO oprOrderParam = new OprOrderParamRequestDTO();
		oprOrderParam.setOrderAmount(requestDTO.getOrderAmount());
		oprOrderParam.setMerchantNo(requestDTO.getOutCustomerNo());
		oprOrderParam.setOrderId(requestDTO.getMerchantOrderId());//商户订单号
		oprOrderParam.setParentMerchantNo(requestDTO.getParentMerchantNo());
		oprOrderParam.setNotifyUrl(Constant.OPR_CALLBACK_URL + Constant.OPR_PAY_CALLBACK);//支付回调地址
		oprOrderParam.setCsUrl(Constant.OPR_CALLBACK_URL + Constant.OPR_CS_CALLBACK);//清算回调地址
		oprOrderParam.setGoodsParamExt(new Gson().toJson(goodsMap));//商品信息
		return oprOrderParam;
	}

	@Override
	public OrderResponseDTO queryReceiptOrderList(OrderRequestDTO requestDTO) {
		if (StringUtils.isBlank(requestDTO.getOrderId())) {
			requestDTO.checkParams();
		}
		UserEntity userEntity = userService.findRegisterByUserNo(requestDTO.getUserNumber());
		AccountOpenEntity accountOpenEntity = applyService.getAccountOpenEntityByUserNumber(userEntity.getUserNumber());
		int totalCount = orderService.getOrderTotalCount(requestDTO.getOrderId(), requestDTO.getStartDate(), requestDTO.getEndDate(),
				requestDTO.getOrderStatus(), requestDTO.getPayType(), accountOpenEntity.getUserNumber());
		int totalPage = (totalCount - 1) / (requestDTO.getPageSize() + 1); // 总页数
		int fromIndex = (requestDTO.getPageIndex() - 1) * requestDTO.getPageSize(); // 每页第一行
		List<Order> orderList = orderService.selectReceiptList(requestDTO.getOrderId(), requestDTO.getStartDate(), requestDTO.getEndDate(),
				requestDTO.getOrderStatus(), requestDTO.getPayType(), accountOpenEntity.getUserNumber(), fromIndex, requestDTO.getPageSize());
		List<OrderInfoDTO> orderDTOList = new OrderDTOConvert().convert2DTO(orderList);
		OrderResponseDTO responseDTO = new OrderResponseDTO();
		responseDTO.setTotalPage(totalPage);
		responseDTO.setTradeCount(totalCount);
		responseDTO.setOrderList(orderDTOList);
		return responseDTO;
	}

	private void checkParams(OrderRequestDTO requestDTO) {
		if (requestDTO == null || StringUtils.isBlank(requestDTO.getUserNumber())
				|| StringUtils.isBlank(requestDTO.getStartDate()) || StringUtils.isBlank(requestDTO.getEndDate())) {
			throw new WbSysException(ErrorCode.MERCHANT_CENTER_PARAM_ERROR);
		}
	}

}