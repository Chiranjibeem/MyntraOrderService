package com.myntra.order.repository;

import com.myntra.order.model.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRegistryRepository extends JpaRepository<Customer, String> {

}
