package com.contoso.service;

import com.contoso.domain.Customer;
import com.contoso.domain.command.CreateCustomerCommand;
import com.contoso.domain.result.CustomerResult;

import java.util.Optional;

public interface CustomerService {

	public Optional<Customer> findById(int customerId);

	public CustomerResult createCustomer(CreateCustomerCommand createCustomerCommand) throws Throwable;
}
