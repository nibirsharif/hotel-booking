package com.contoso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.domain.Room;
import com.contoso.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Optional<Room> findById(int roomId) {
		return roomRepository.findById(roomId);
	}

	@Override
	public void updateRoom(Room room) {
		roomRepository.saveAndFlush(room);
	}
}
