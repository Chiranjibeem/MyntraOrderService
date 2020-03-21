package com.myntra.order.repository;

import com.myntra.order.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRegistryRepository extends JpaRepository<Payment, String> {
}
