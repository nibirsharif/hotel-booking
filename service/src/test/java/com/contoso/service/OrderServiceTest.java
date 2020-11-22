package com.contoso.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contoso.domain.Customer;
import com.contoso.domain.Order;
import com.contoso.domain.Room;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;
import com.contoso.repository.OrderRepository;
import com.contoso.service.command.CreateOrder;
import com.contoso.service.command.PopulateOrderResult;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
	
	@MockBean
	private OrderRepository orderRepository;
	
	@InjectMocks
	private OrderServiceImpl orderService;

	@MockBean
	private CreateOrder createOrder;
	
	@MockBean
	private PopulateOrderResult populateOrderResult;
	
	private Order order;
	
	private CreateOrderCommand createOrderCommand;
	
	private OrderResult orderResult;
	
	@BeforeEach
	public void setup() {
		createMockOrder();
	}
	
	private void createMockOrder() {
		order = new Order();
		order.setCheckInDate(new Date());
		order.setCheckInDate(new Date());
		order.setCustomer(new Customer());
		order.setRoom(new Room());
		order.setTotalPrice(100L);
	}
	
	private void createMockCustomerCommand() {
		createOrderCommand = new CreateOrderCommand();
		createOrderCommand.setCustomerId(1);
		createOrderCommand.setRoomId(1);
		createOrderCommand.setCheckInDate(new Date());
		createOrderCommand.setCheckOutDate(new Date());
	}
	
	private void createMockResult() {
		orderResult = new OrderResult();
	}

	@Test
	public void shouldPassGetOrderById() throws Throwable {
		createMockOrder();
		
		when(this.orderService.findById(1)).thenReturn(Optional.of(order));

		Optional<Order> order = this.orderService.findById(1);

		assertNotNull(order);
	}
	
	@Test
	public void shouldPassCreateOrder() throws Throwable {
		createMockCustomerCommand();
		createMockResult();
		
		when(this.createOrder.create(createOrderCommand)).thenReturn(order);
		when(this.orderService.createOrder(createOrderCommand)).thenReturn(orderResult);

		OrderResult result = this.orderService.createOrder(createOrderCommand);

		assertNotNull(result);
	}
}
