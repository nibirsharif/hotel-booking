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

import com.contoso.domain.Hotel;
import com.contoso.repository.HotelRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class HotelServiceTest {
	
	@MockBean
	private HotelRepository hotelRepository;
	
	@InjectMocks
	private HotelServiceImpl hotelService;

	private Hotel hotel;

	@BeforeEach
	public void setup() {
		hotel = new Hotel();
		hotel.setName("Pacific Place");
	}

	@Test
	public void shouldPassGetHotelById() throws Exception {
		when(this.hotelService.findById(1)).thenReturn(Optional.of(hotel));

		Optional<Hotel> hotel = this.hotelService.findById(1);

		assertNotNull(hotel);
	}
}
