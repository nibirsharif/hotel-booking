package com.contoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contoso.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
