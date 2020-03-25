package com.myntra.order.repository;

import com.myntra.order.model.Customer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRegistryRepository extends JpaRepository<Customer, String> {

    @Query(value = "select * from M_CUSTOMER where cust_id = :username and cust_pwd = :password ", nativeQuery = true)
    List<Customer> findCustomerByIdAndPassword(String username, String password);

}
