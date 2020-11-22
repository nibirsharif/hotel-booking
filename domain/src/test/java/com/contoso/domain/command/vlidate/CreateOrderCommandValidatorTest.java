package com.contoso.domain.command.vlidate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.command.validate.CreateOrderCommandValidator;

public class CreateOrderCommandValidatorTest {
	
	private ConstraintValidatorContext context;
	
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

	private CreateOrderCommandValidator validator;
	
	private CreateOrderCommand command;
	
	@BeforeEach
	public void setup() {
		context = mock(ConstraintValidatorContext.class);
		constraintViolationBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
		
		validator = new CreateOrderCommandValidator();
		command = new CreateOrderCommand();
		command.setCheckInDate(new Date());
		command.setCheckOutDate(new Date());
		
		when(context.buildConstraintViolationWithTemplate(Mockito.anyString())).thenReturn(constraintViolationBuilder);
	}
	
	@Test
	public void shouldReturnTrue() throws Throwable {
		assertTrue(validator.isValid(command, context));
	}
	
	@Test
	public void shouldReturnFalseWhenCheckOutDateIsBeforeCheckInDate() throws Throwable {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Date checkInDate = calendar.getTime();
		
		command.setCheckInDate(checkInDate);
		command.setCheckOutDate(date);
		
		assertFalse(validator.isValid(command, context));
	}
	
	@Test
	public void shouldReturnFalseWhenCheckInDateIsMoreThanSixMonthsFromToday() throws Throwable {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 7);
		Date checkInDate = calendar.getTime();
		
		calendar.setTime(checkInDate);
		calendar.add(Calendar.DATE, 1);
		Date checkOutDate = calendar.getTime();
		
		command.setCheckInDate(checkInDate);
		command.setCheckOutDate(checkOutDate);
		
		assertFalse(validator.isValid(command, context));
	}
}
