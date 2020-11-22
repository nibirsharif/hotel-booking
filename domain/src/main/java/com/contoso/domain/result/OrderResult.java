package com.contoso.domain.result;

import java.util.Date;

import com.contoso.domain.Customer;
import com.contoso.domain.Hotel;
import com.contoso.domain.Room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResult {

	private Integer id;
	
	private Date checkInDate;
	
	private Date checkOutDate;
	
	private Hotel hotel;
	
	private Room room;
	
	private Customer customer;
	
	private Long totalAmount;
}
