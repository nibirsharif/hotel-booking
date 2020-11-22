package com.contoso.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contoso.domain.Order;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OrderRepositoryTest {

	@MockBean
	private OrderRepository orderRepository;
	
	private Order mockedOrder;
	
	@BeforeEach
	public void setup() {
		mockedOrder = new Order();
		mockedOrder.setTotalPrice(100L);
	}
	
	@Test
	public void shouldPassGetOrderById() throws Exception {
		when(this.orderRepository.findById(1)).thenReturn(Optional.of(mockedOrder));

		Optional<Order> order = this.orderRepository.findById(1);

		assertNotNull(order);
	}
	
	@Test
	public void shouldPassSaveOrder() throws Exception {
		Order order = new Order();
		
		when(this.orderRepository.save(order)).thenReturn(mockedOrder);
		
		Order result = this.orderRepository.save(order);
		
		assertThat(result).hasFieldOrPropertyWithValue("totalPrice", 100L);
	}
}
