package com.contoso.service;

import java.util.Optional;

import com.contoso.domain.Order;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;

public interface OrderService {

	public Optional<Order> findById(int orderId);
	
	public OrderResult createOrder(CreateOrderCommand createOrderCommand) throws Throwable;
}
