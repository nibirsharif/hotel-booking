package com.contoso.controller;

import com.contoso.domain.command.CreateCustomerCommand;
import com.contoso.domain.result.CustomerResult;
import com.contoso.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResult createCustomer(@Valid @RequestBody CreateCustomerCommand createCustomerCommand) throws Throwable {
        return service.createCustomer(createCustomerCommand);
    }
}
