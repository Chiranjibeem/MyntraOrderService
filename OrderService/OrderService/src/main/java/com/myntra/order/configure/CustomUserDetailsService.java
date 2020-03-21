package com.myntra.order.configure;

import com.myntra.order.controller.OrderRegistryContoller;
import com.myntra.order.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OrderRegistryContoller controller;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = controller.getCustomer(s);
        UserDetails userDetails = new CustomUserDetails(customer);
        return userDetails;
    }
}
