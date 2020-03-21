package com.myntra.order.service;

import com.myntra.order.model.Order;
import com.myntra.order.repository.OrderRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderRegistryService {

    @Autowired
    private OrderRegistryRepository repository;

    public Order createOrder(Order order){
        return repository.save(order);
    }
}
