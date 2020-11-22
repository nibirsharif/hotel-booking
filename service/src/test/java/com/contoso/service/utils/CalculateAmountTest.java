package com.contoso.service.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.contoso.domain.command.CreateOrderCommand;

public class CalculateAmountTest {

	private CalculateAmount calculateAmount;
	
	private CreateOrderCommand createOrderCommand;
	
	@BeforeEach
	public void setup() {
		calculateAmount = new CalculateAmount();
		createMockCustomerCommand();
	}
	
	private void createMockCustomerCommand() {
		Date checkInDate = new Date();
		
		createOrderCommand = new CreateOrderCommand();
		createOrderCommand.setCheckInDate(checkInDate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(checkInDate);
		calendar.add(Calendar.DATE, 2);
		Date checkOutDate = calendar.getTime();
		
		createOrderCommand.setCheckOutDate(checkOutDate);
	}

	@Test
	public void shouldPassCalculateAmount() throws Throwable {
		Long amount = calculateAmount.getAmount(100L, createOrderCommand);
		
		assertNotNull(amount);
		assertEquals(200L, amount);
	}
}
