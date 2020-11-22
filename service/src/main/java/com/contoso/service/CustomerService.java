package com.contoso.service;

import java.util.Optional;

import com.contoso.domain.Customer;

public interface CustomerService {

	public Optional<Customer> findById(int customerId);
}
