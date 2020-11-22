package com.contoso.service;

import java.util.Optional;

import com.contoso.domain.Room;

public interface RoomService {

	public Optional<Room> findById(int roomId);
	
	public void updateRoom(Room room);
}
