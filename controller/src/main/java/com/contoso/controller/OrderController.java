package com.contoso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;
import com.contoso.service.OrderService;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {
	
	@Autowired
	private OrderService service;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
	public OrderResult createOrder(@Valid @RequestBody CreateOrderCommand createOrderCommand) throws Throwable {
        return service.createOrder(createOrderCommand);
    }
}
