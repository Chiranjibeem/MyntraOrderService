package com.myntra.order.service;

import com.myntra.order.model.Payment;
import com.myntra.order.repository.PaymentRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentRegistryService {

    @Autowired
    PaymentRegistryRepository repository;

    public Payment fetchPaymentType(String paymentType) {
        Payment payment = null;
        try {
            payment = repository.findById(paymentType).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
