package com.contoso.domain.command;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderCommand {

	@NotNull(message = "Customer Id cannot be null")
	private Integer customerId;
	
	@NotNull(message = "Room Id cannot be null")
	private Integer roomId;
	
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
