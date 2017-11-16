package com.yeepay.g3.core.whitebroad.parser.impl;

import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import com.yeepay.g3.core.whitebroad.parser.DTOConvert;
import com.yeepay.g3.core.whitebroad.util.DateUtils;
import com.yeepay.g3.facade.whitebroad.dto.trade.OrderInfoDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDTOConvert implements DTOConvert<OrderInfoDTO, Order> {

	@Override
	public Order convert2Entity(OrderInfoDTO d) {
		return null;
	}

	@Override
	public OrderInfoDTO convert2DTO(Order entity) {
		OrderInfoDTO orderDto = new OrderInfoDTO();
		if(entity != null) {
			BeanUtils.copyProperties(entity, orderDto);
			orderDto.setTrxAmt(entity.getTrxAmt().toPlainString());
			orderDto.setOrderType(entity.getOrderType().getValue());
            orderDto.setPayType(entity.getPayType() != null ? entity.getPayType().getValue() : null);
            orderDto.setOrderStatus(entity.getOrderStatus().getValue());
			orderDto.setCompleteTime(DateUtils.toString(entity.getCompleteTime(), DateUtils.DATE_FORMAT_DATETIME));
		}
		return orderDto;
	}

	public List<OrderInfoDTO> convert2DTO(List<Order> entityList) {
		List<OrderInfoDTO> settleDTOList = new ArrayList<OrderInfoDTO>();
		for (Order entity : entityList) {
			settleDTOList.add(convert2DTO(entity));
		}
		return settleDTOList;
	}
}