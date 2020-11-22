package com.contoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contoso.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}

