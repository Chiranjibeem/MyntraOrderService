package com.myntra.order.controller;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.*;
import com.myntra.order.service.OrderRegistryService;
import com.myntra.order.util.CustomerUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderRegistryContoller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRegistryService service;

    @Autowired
    private CustomerUtil customerUtil;

    public String getDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(date);
    }

    @RequestMapping(value = "/createOrder/{custID}/{itemID}/{paymentType}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('Customer')")
    public ResponseEntity<Object> createOrder(@PathVariable(name = "custID") String custID,
                                              @PathVariable(name = "itemID") String itemID, @PathVariable(name = "paymentType") String paymentType)
            throws OrderException {

        Customer customer = null;
        try {
            customer = customerUtil.getCustomer(custID);
        } catch (Exception e) {
            throw new OrderException("There is an Error While Fetching Customer Details " + e);
        }
        Order order = new Order();
        order.setOrderId("O-" + getDate(new Date()));
        if (customer != null) {
            customer.setOrderId(order.getOrderId());
            String[] str = itemID.split(",");
            List<Item> itemList = new ArrayList<Item>();
            BigDecimal price = BigDecimal.ZERO;
            for (int i = 0; i < str.length; i++) {
                Item itm = null;
                try {
                    itm = restTemplate.getForObject("http://ITEM-REGISTRY/addItem/" + str[i], Item.class);
                } catch (Exception e) {
                    throw new OrderException("There is an Error While Fetching Item Details " + e);
                }
                if (itm != null) {
                    ItemEmbeddedID itemId = new ItemEmbeddedID();
                    itemId.setItemId(itm.getItemId());
                    itemId.setOrderId(order.getOrderId());
                    itm.setItm(itemId);
                    itm.setItemId(itemId.getItemId());
                    itemList.add(itm);
                    price = price.add(itm.getItemPrice());
                } else {
                    throw new OrderException("Item Not Found In Database. ItemID :" + str[i]);
                }
            }
            Payment payment = null;
            try {
                payment = restTemplate
                        .getForObject("http://PAYMENT-REGISTRY/createPayment/" + paymentType + "/" + price, Payment.class);
            } catch (Exception e) {
                throw new OrderException("There is an Error While Fetching Payment Details " + e);
            }
            if (payment != null) {
                payment.setOrderId(order.getOrderId());
                payment.setPaymentAmount(price);
                order.setCustomer(customer);
                order.setPayment(payment);
                order.setItem(itemList);
                order.setAmount(price);
                Order newOrder = null;
                try {
                    newOrder = service.createOrder(order);
                } catch (Exception e) {
                    throw new OrderException("There is an error while saving record");
                }
                if (newOrder != null) {
                    return new ResponseEntity<Object>(newOrder, HttpStatus.OK);
                } else {
                    throw new OrderException("There is an error while saving record");
                }
            } else {
                throw new OrderException("Payment Type Not Found In The Database. PaymentType :" + paymentType);
            }
        } else {
            throw new OrderException("Customer Not Found In the Databse.Customer Id : " + custID);
        }
    }

    @RequestMapping(value = "/findOrder/{orderNo}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('Customer','Admin')")
    @ResponseBody
    public ResponseEntity<Object> findOrderDetails(@PathVariable(name = "orderNo") String orderNo) {
        if (orderNo != null) {
            Order order = service.findOrder(orderNo);
            if (order != null) {
                return new ResponseEntity<Object>(order, HttpStatus.OK);
            } else {
                throw new OrderException("There is no Order exist in the database, Please enter different order no " + orderNo);
            }
        } else {
            throw new OrderException("Order No Can Not Be Null, Please enter Order No and try Again");
        }
    }

    @RequestMapping(value = "/findOrderByCustomer/{custID}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('Customer')")
    @ResponseBody
    public ResponseEntity<Object> findOrderByCustomer(@PathVariable(name = "custID") String custID) {
        if (custID != null) {
            List<Customer> customerList = service.findOrderByCustomer(custID);
            if (customerList != null && !customerList.isEmpty()) {
                List<Order> orderList = customerList.stream().map(i -> new Order(i.getOrder())).collect(Collectors.toList());
                return new ResponseEntity<Object>(orderList, HttpStatus.OK);
            } else {
                throw new OrderException("There is no Order exist in the database, Please enter different customer no " + custID);
            }
        } else {
            throw new OrderException("Customer Id Can Not Be Null, Please enter cutsomer id and try Again");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage() {
        return "Login Page";
    }


}