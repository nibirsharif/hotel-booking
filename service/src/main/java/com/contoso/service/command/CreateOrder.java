package com.contoso.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.contoso.domain.Customer;
import com.contoso.domain.Order;
import com.contoso.domain.Room;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.service.CustomerService;
import com.contoso.service.RoomService;
import com.contoso.service.utils.CalculateAmount;

@Component
public class CreateOrder implements BaseModel<Order, CreateOrderCommand> {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CalculateAmount calculateAmount;

	@Override
	public Order create(CreateOrderCommand createOrderCommand) throws Throwable {
		
		try {
			Customer customer = customerService.findById(createOrderCommand.getCustomerId()).get();
			Room room = roomService.findById(createOrderCommand.getRoomId()).get();
			
			room.setNumberOfGuests(createOrderCommand.getNumberOfGuests());
			roomService.updateRoom(room);
			
			Order order = new Order();
			order.setCheckInDate(createOrderCommand.getCheckInDate());
			order.setCheckOutDate(createOrderCommand.getCheckOutDate());
			order.setCustomer(customer);
			order.setRoom(room);
			
			Long amount = calculateAmount.getAmount(room.getCurrentPrice(), createOrderCommand);
			order.setTotalPrice(amount);
			
			return order;
		} catch (Exception e) {
			throw new Exception("Exception while creating order. " + e.getMessage());
		}
	}
}
