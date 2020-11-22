package com.contoso.service.command;

import org.springframework.stereotype.Component;

import com.contoso.domain.Order;
import com.contoso.domain.result.OrderResult;

@Component
public class PopulateOrderResult implements BaseResult<OrderResult, Order> {

	@Override
	public OrderResult populate(Order order) {
		OrderResult result = new OrderResult();
		
		result.setId(order.getId());
		result.setCheckInDate(order.getCheckInDate());
		result.setCheckOutDate(order.getCheckOutDate());
		result.setHotel(order.getRoom().getHotel());
		result.setRoom(order.getRoom());
		result.setCustomer(order.getCustomer());
		result.setTotalAmount(order.getTotalPrice());
		
		return result;
	}
}
