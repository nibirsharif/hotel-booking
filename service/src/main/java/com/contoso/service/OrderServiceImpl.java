package com.contoso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.domain.Order;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;
import com.contoso.repository.OrderRepository;
import com.contoso.service.command.CreateOrder;
import com.contoso.service.command.PopulateOrderResult;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CreateOrder createOrder;
	
	@Autowired
	private PopulateOrderResult populateResult;
	
	@Override
	public Optional<Order> findById(int orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	public OrderResult createOrder(CreateOrderCommand createOrderCommand) throws Throwable {
		Order order = createOrder.create(createOrderCommand);
		orderRepository.saveAndFlush(order);
		
		return populateResult.populate(order);
	}

}
