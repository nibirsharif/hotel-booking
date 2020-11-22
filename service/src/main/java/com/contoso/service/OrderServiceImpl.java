package com.contoso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.domain.Order;
import com.contoso.domain.command.CreateOrderCommand;
import com.contoso.domain.result.OrderResult;
import com.contoso.repository.OrderRepository;
import com.contoso.service.command.CreateOrder;
import com.contoso.service.command.PopulateOrderResult;

@Service
public class OrderServiceImpl implements OrderService {

	/*@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CustomerRepository customerRepository;*/
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CreateOrder createOrder;
	
	@Autowired
	private PopulateOrderResult populateResult;
	
	@Override
	public Optional<Order> findById(int orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	public OrderResult createOrder(CreateOrderCommand createOrderCommand) throws Throwable {
		Order order = createOrder.create(createOrderCommand);
		orderRepository.saveAndFlush(order);
		
		return populateResult.populate(order);
	}
	
	/*
	 * Only use first time to create sample records in database.
	 * Create Order API will insert Order record based on these data.
	 * Assumes Hotel, Room and Customer are already present in database.
	 */
	/*@PostConstruct
	public void initModels() {
		Hotel hotel = new Hotel();
		hotel.setName("Pacific Place");
		hotelRepository.save(hotel);
		
		Room room = new Room();
		room.setName("A-1");
		room.setNumberOfGuests(1);
		room.setCurrentPrice(100L);
		room.setHotel(hotel);
		roomRepository.save(room);
		
		Customer customer = new Customer();
		customer.setFirstName("Shariful");
		customer.setLastName("Nibir");
		customer.setNumber("0123456780");
		customer.setEmail("example@contoso.com");
		customerRepository.save(customer);
	}*/

}
