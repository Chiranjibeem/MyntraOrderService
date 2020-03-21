package com.myntra.order.controller;

import com.myntra.order.model.Payment;
import com.myntra.order.service.PaymentRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PaymentRegistryController {

    @Autowired
    PaymentRegistryService service;

    @RequestMapping(value = "/createPayment/{paymentType}/{amount}")
    public Payment createPayment(@PathVariable(name = "paymentType") String type,
                                 @PathVariable(name = "amount") BigDecimal amount) {
        return service.fetchPaymentType(type);
    }

}
