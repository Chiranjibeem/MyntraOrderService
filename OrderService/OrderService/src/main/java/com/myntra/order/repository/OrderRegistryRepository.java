package com.myntra.order.repository;

import com.myntra.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRegistryRepository extends JpaRepository<Order,String>{
}
