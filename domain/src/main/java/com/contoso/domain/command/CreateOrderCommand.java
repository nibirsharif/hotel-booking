package com.contoso.domain.command;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.contoso.domain.command.validate.ValidateCreateOrderCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidateCreateOrderCommand
public class CreateOrderCommand {

	@Min(value = 1, message = "Id should starts from 1")
	@NotNull(message = "Customer Id cannot be null")
	private Integer customerId;
	
	@Min(value = 1, message = "Id should starts from 1")
	@NotNull(message = "Room Id cannot be null")
	private Integer roomId;
	
//	@ValidateCreateOrderCommand
	@NotNull(message = "Check in date cannot be null")
	@FutureOrPresent(message = "Check in date can not be past")
	private Date checkInDate;
	
	@NotNull(message = "Check out date cannot be null")
	@FutureOrPresent(message = "Check in date can not be past")
	private Date checkOutDate;
	
	@Min(value = 1, message = "Guests should not be less than 1")
    @Max(value = 2, message = "Guests should not be greater than 2")
	@NotNull(message = "Number of guests cannot be null")
	private Integer numberOfGuests;
}
