package com.contoso.service;

import java.util.Optional;

import com.contoso.domain.Hotel;

public interface HotelService {

	public Optional<Hotel> findById(int hotelId);
}
