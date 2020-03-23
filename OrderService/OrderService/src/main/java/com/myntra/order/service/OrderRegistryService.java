package com.myntra.order.service;

import com.myntra.order.model.Customer;
import com.myntra.order.model.Order;
import com.myntra.order.repository.CustomerRegistryRepository;
import com.myntra.order.repository.OrderRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrderRegistryService {

    @Autowired
    private OrderRegistryRepository orderRepository;

    @Autowired
    private CustomerRegistryRepository customerRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public Order findOrder(String orderNo){
        Order order= null;
        try {
            order = orderRepository.findById(orderNo).get();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return order;
    }

    public List<Customer> findOrderByCustomer(String custID){
       return customerRepository.findAllOrderByCustomer(custID);
    }
}
