package com.contoso.service.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.contoso.domain.Customer;
import com.contoso.domain.Order;
import com.contoso.domain.Room;
import com.contoso.domain.result.OrderResult;

public class PopulateOrderResultTest {
	
	private PopulateOrderResult populateOrderResult;

	private Order order;
	
	@BeforeEach
	public void setup() {
		populateOrderResult = new PopulateOrderResult();
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
	
	@Test
	public void shouldPassPopulateOrderResult() throws Throwable {
		OrderResult result = populateOrderResult.populate(order);
		
		assertNotNull(result);
		assertEquals(order.getCheckInDate(), result.getCheckInDate());
		assertEquals(order.getCheckOutDate(), result.getCheckOutDate());
		assertEquals(order.getTotalPrice(), result.getTotalAmount());
	}
}
