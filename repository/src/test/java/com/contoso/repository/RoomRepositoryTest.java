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

import com.contoso.domain.Room;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoomRepositoryTest {

	@MockBean
	private RoomRepository roomRepository;
	
	private Room mockedRoom;
	
	@BeforeEach
	public void setup() {
		mockedRoom = new Room();
		mockedRoom.setName("A-1");
	}
	
	@Test
	public void shouldPassGetRoomById() throws Exception {
		when(this.roomRepository.findById(1)).thenReturn(Optional.of(mockedRoom));

		Optional<Room> room = this.roomRepository.findById(1);

		assertNotNull(room);
		assertEquals(mockedRoom.getName(), room.get().getName());
	}
}
