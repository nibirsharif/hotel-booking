package com.contoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contoso.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}

