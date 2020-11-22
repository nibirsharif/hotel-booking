package com.contoso.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.contoso.controller.errorhandling.CustomRestExceptionHandler;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;
import com.contoso.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

    private MockMvc mvc;
	
	@InjectMocks
	private OrderController controller;
	
	@MockBean
    private OrderService service;
	
	private CreateOrderCommand command;
	
	private OrderResult result;
	
	private JacksonTester<CreateOrderCommand> jsonOrderCommand;
	
	@BeforeEach
	public void setup() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Date checkInDate = calendar.getTime();
		
		calendar.setTime(checkInDate);
		calendar.add(Calendar.DATE, 1);
		Date checkOutDate = calendar.getTime();
		
		command = new CreateOrderCommand();
		command.setCheckInDate(checkInDate);
		command.setCheckOutDate(checkOutDate);
		command.setCustomerId(1);
		command.setRoomId(1);
		command.setNumberOfGuests(1);
		result = new OrderResult();
		
		JacksonTester.initFields(this, new ObjectMapper());
		
		mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
	}
	
	@Test
	public void shouldResultIsCreated() throws Throwable {
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
			      .andExpect(status().isCreated());
	}
	
	@Test
	public void souldReturnBadRequestWhenCheckOutDateIsBeforeCheckInDate() throws Throwable {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Date checkInDate = calendar.getTime();
		
		command.setCheckInDate(checkInDate);
		command.setCheckOutDate(date);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
				  .andExpect(status().isBadRequest())
				  .andExpect(MockMvcResultMatchers.jsonPath("$.errors", hasItem("createOrderCommand: Check out date should be after check in date.")));
	}
	
	@Test
	public void souldReturnBadRequestWhenCheckInDateIsMoreThanSixMonthsFromToday() throws Throwable {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 7);
		Date checkInDate = calendar.getTime();
		command.setCheckInDate(checkInDate);
		
		calendar.setTime(checkInDate);
		calendar.add(Calendar.DATE, 1);
		Date checkOutDate = calendar.getTime();
		command.setCheckOutDate(checkOutDate);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
				  .andExpect(status().isBadRequest())
				  .andExpect(MockMvcResultMatchers.jsonPath("$.errors", hasItem("createOrderCommand: Check in date can not be more than six months from now.")));
	}
	
	@Test
	public void souldReturnBadRequestWhenNumberOfGuestsIsLessThanOne() throws Throwable {
		command.setNumberOfGuests(0);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
			      .andExpect(status().isBadRequest())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.errors", hasItem("numberOfGuests: Guests should not be less than 1")));
	}
	
	@Test
	public void souldReturnBadRequestWhenNumberOfGuestsIsMoreThanTwo() throws Throwable {
		command.setNumberOfGuests(3);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
			      .andExpect(status().isBadRequest());
	}
	
	@Test
	public void souldReturnBadRequestWhenCustomerIdIsNotPresent() throws Throwable {
		command.setCustomerId(null);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
			      .andExpect(status().isBadRequest());
	}
	
	@Test
	public void souldReturnBadRequestWhenRoomIdIsNotPresent() throws Throwable {
		command.setRoomId(null);
		
		when(this.service.createOrder(command)).thenReturn(result);
		
		mvc.perform(post("/api/v1/order/createorder")
			      .contentType(MediaType.APPLICATION_JSON)
				  .content(jsonOrderCommand.write(command).getJson()))
			      .andExpect(status().isBadRequest());
	}
}
