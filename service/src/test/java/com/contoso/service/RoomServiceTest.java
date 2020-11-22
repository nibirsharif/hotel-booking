package com.contoso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.contoso.domain.Hotel;
import com.contoso.domain.Room;
import com.contoso.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoomServiceTest {
	
	@MockBean
	private RoomRepository mockRepository;

	@InjectMocks
	private RoomServiceImpl roomService;

	private Hotel hotel;
	
	private Room room;

	@BeforeEach
	public void setup() {
		hotel = new Hotel();
		hotel.setName("Pacific Place");

		room = new Room();
		room.setName("A1-2");
		room.setNumberOfGuests(1);
		room.setCurrentPrice(100L);
		room.setHotel(hotel);
	}

	@Test
	public void shouldPassGetRoomById() throws Exception {
		when(this.roomService.findById(1)).thenReturn(Optional.of(room));

		Optional<Room> room = this.roomService.findById(1);

		assertNotNull(room);
	}

	@Test
	public void shouldPassUpdateRoomBy() throws Exception {
		this.roomService.updateRoom(room);

		ArgumentCaptor<Room> argument = ArgumentCaptor.forClass(Room.class);
		verify(this.mockRepository).saveAndFlush(argument.capture());
		
		assertEquals(room, argument.getValue());
	}
}
