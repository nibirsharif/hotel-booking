package com.contoso.service;

import com.contoso.domain.Customer;
import com.contoso.domain.command.CreateCustomerCommand;
import com.contoso.domain.result.CustomerResult;
import com.contoso.repository.CustomerRepository;
import com.contoso.service.command.CreateCustomer;
import com.contoso.service.command.PopulateCustomerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CreateCustomer createCustomer;

	@Autowired
	private PopulateCustomerResult populateResult;
	
	@Override
	public Optional<Customer> findById(int customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public CustomerResult createCustomer(CreateCustomerCommand createCustomerCommand) throws Throwable {
		Customer customer = createCustomer.create(createCustomerCommand);
		customerRepository.saveAndFlush(customer);

		return populateResult.populate(customer);
	}

}
