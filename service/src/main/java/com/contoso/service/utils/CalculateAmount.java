package com.contoso.service.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.contoso.domain.command.CreateOrderCommand;

@Component
public class CalculateAmount {

	public Long getAmount(long price, CreateOrderCommand createOrderCommand) {
		Date checkInDate = createOrderCommand.getCheckInDate();
	    Date checkOutDate = createOrderCommand.getCheckOutDate();
	    Long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		return diff == 0 ? 1 * price : diff * price;
	}
}
