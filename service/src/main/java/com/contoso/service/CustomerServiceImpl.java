package com.contoso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.domain.Customer;
import com.contoso.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> findById(int customerId) {
		return customerRepository.findById(customerId);
	}

}
