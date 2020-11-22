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

import com.contoso.domain.Hotel;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class HotelRepositoryTest {

	@MockBean
	private HotelRepository hotelRepository;
	
	private Hotel mockedHotel;

	@BeforeEach
	public void setup() {
		mockedHotel = new Hotel();
		mockedHotel.setName("Pacific Place");
	}
	
	@Test
	public void shouldPassGetHotelById() throws Exception {
		when(this.hotelRepository.findById(1)).thenReturn(Optional.of(mockedHotel));

		Optional<Hotel> hotel = this.hotelRepository.findById(1);

		assertNotNull(hotel);
		assertEquals(mockedHotel.getName(), hotel.get().getName());
	}
}
