package com.contoso.service.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.contoso.repository.CustomerRepository;
import com.contoso.repository.OrderRepository;
import com.contoso.repository.RoomRepository;
import com.contoso.service.CustomerService;
import com.contoso.service.OrderService;
import com.contoso.service.RoomService;
import com.contoso.service.utils.CalculateAmount;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CreateOrderTest {
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private OrderService orderService;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private RoomService roomService;
	
	@MockBean
	private CalculateAmount calculateAmount;

	@InjectMocks
	private CreateOrder createOrder;
	
	private CreateOrderCommand createOrderCommand;

	@BeforeEach
	public void setup() {
		createMockCustomerCommand();
	}
	
	private void createMockCustomerCommand() {
		createOrderCommand = new CreateOrderCommand();
		createOrderCommand.setCustomerId(1);
		createOrderCommand.setRoomId(1);
		createOrderCommand.setCheckInDate(new Date());
		createOrderCommand.setCheckOutDate(new Date());
	}
	
	@Test
	public void shouldPassCreateOrder() throws Throwable {
		Room room = new Room();
		room.setCurrentPrice(100L);
		
		when(this.customerService.findById(1)).thenReturn(Optional.of(new Customer()));
		when(this.roomService.findById(1)).thenReturn(Optional.of(room));
		when(this.calculateAmount.getAmount(room.getCurrentPrice(), createOrderCommand)).thenReturn(100L);
		
		Order order = this.createOrder.create(createOrderCommand);
		
		assertNotNull(order);
		assertEquals(createOrderCommand.getCheckInDate(), order.getCheckInDate());
		assertEquals(createOrderCommand.getCheckOutDate(), order.getCheckOutDate());
		assertEquals(100L, order.getTotalPrice());
	}
	
	@Test
	public void shouldReturnErrorWhenCustomerIsNotFound() throws Throwable {
		
		Exception exception = assertThrows(Exception.class, () -> {
			this.createOrder.create(createOrderCommand);
	    });
		String expectedMessage = "Exception while creating order.";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void shouldReturnErrorWhenRoomIsNotFound() throws Throwable {
		
		when(this.customerService.findById(1)).thenReturn(Optional.of(new Customer()));
		
		Exception exception = assertThrows(Exception.class, () -> {
			this.createOrder.create(createOrderCommand);
	    });
		String expectedMessage = "Exception while creating order.";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
