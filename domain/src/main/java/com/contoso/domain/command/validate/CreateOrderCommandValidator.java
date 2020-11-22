package com.contoso.domain.command.validate;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.contoso.domain.command.CreateOrderCommand;

//public class CreateOrderCommandValidator implements ConstraintValidator<ValidateCreateOrderCommand, Date> {
//
//	private static final int MAX_MONTHS = 6;
//	
//	@Override
//	public boolean isValid(Date checkInDate, ConstraintValidatorContext context) {
//		LocalDate currentDate =LocalDate.now();
//		LocalDate futureDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		Period period = Period.between(currentDate, futureDate);
//		return period.getMonths() > MAX_MONTHS ? false : true;
//	}
//}
public class CreateOrderCommandValidator implements ConstraintValidator<ValidateCreateOrderCommand, CreateOrderCommand> {

	private static final int MAX_MONTHS = 6;
	
	@Override
	public boolean isValid(CreateOrderCommand command, ConstraintValidatorContext context) {
		boolean isValid = true;
		String message = "";
		
		if (command.getCheckInDate().compareTo(command.getCheckOutDate()) > 0) {
			isValid = false;
			message = "Check out date should be after check in date.";
			setErrorMessage(message, context);
		}
		
		if (isCheckInDateMoreThanSixMonthsFromNow(command.getCheckInDate())) {
			isValid = false;
			message = "Check in date can not be more than six months from now.";
			setErrorMessage(message, context);
		}
		
		return isValid;
	}
	
	private boolean isCheckInDateMoreThanSixMonthsFromNow(Date checkInDate) {
		LocalDate currentDate =LocalDate.now();
		LocalDate futureDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(currentDate, futureDate);
		return period.getMonths() > MAX_MONTHS;
	}
	
	private void setErrorMessage(String message, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
	    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}
}