package com.contoso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.domain.Hotel;
import com.contoso.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Optional<Hotel> findById(int hotelId) {
		return hotelRepository.findById(hotelId);
	}

}
