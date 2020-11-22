package com.contoso.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contoso.domain.Customer;
import com.contoso.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer mockedCustomer;

	@BeforeEach
	public void setup() {
		mockedCustomer = new Customer();
		mockedCustomer.setFirstName("Shariful");
		mockedCustomer.setLastName("Nibir");
		mockedCustomer.setNumber("0123456789");
		mockedCustomer.setEmail("example@domain.com");
	}

	@Test
	public void shouldPassGetCustomerById() throws Exception {
		when(this.customerService.findById(1)).thenReturn(Optional.of(mockedCustomer));

		Optional<Customer> customer = this.customerService.findById(1);

		assertNotNull(customer);
	}
}
