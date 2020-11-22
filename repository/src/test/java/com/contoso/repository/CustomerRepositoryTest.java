package com.contoso.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contoso.domain.Customer;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

	@MockBean
	private CustomerRepository customerRepository;
	
	private Customer mockCustomer;

	@BeforeEach
	public void setup() {
		mockCustomer = new Customer();
		mockCustomer.setFirstName("Shariful");
		mockCustomer.setLastName("Nibir");
		mockCustomer.setNumber("0123456789");
		mockCustomer.setEmail("example@contoso.com");
	}

	@Test
	public void shouldPassGetCustomerById() throws Exception {
		when(this.customerRepository.findById(1)).thenReturn(Optional.of(mockCustomer));

		Optional<Customer> customer = this.customerRepository.findById(1);

		assertNotNull(customer);
		assertEquals(mockCustomer.getFirstName(), customer.get().getFirstName());
		assertEquals(mockCustomer.getLastName(), customer.get().getLastName());
		assertEquals(mockCustomer.getNumber(), customer.get().getNumber());
		assertEquals(mockCustomer.getEmail(), customer.get().getEmail());
	}
}
