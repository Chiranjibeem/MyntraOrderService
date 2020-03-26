package com.myntra.order.util;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerUtil {

    @Autowired
    RestTemplate restTemplate;

    public Customer getCustomer(String... str) throws OrderException {
        Customer customer = null;
        try {
            if (str.length > 1) {
                customer = restTemplate.getForObject("http://CUSTOMER-REGISTRY/fetchCustomer/" + str[0] + "/" + str[1],
                        Customer.class);

            } else {
                customer = restTemplate.getForObject("http://CUSTOMER-REGISTRY/fetchCustomer/" + str[0],
                        Customer.class);
            }
            return customer;
        } catch (Exception e) {
            throw new OrderException(e.getMessage());
        }
    }
}
