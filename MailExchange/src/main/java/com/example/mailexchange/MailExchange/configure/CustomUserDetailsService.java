package com.example.mailexchange.MailExchange.configure;

import com.example.mailexchange.MailExchange.model.Customer;
import com.example.mailexchange.MailExchange.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRegistryService customerRegistryService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerRegistryService.findCustomerDetails(s);
        UserDetails userDetails = new CustomUserDetails(customer);
        return userDetails;
    }
}
