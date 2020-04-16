package com.example.mailexchange.MailExchange.repository;

import com.example.mailexchange.MailExchange.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRegistryRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select * from ME_CUSTOMER where CUST_EMAIL = :custEmail and CUST_PWD = :password ", nativeQuery = true)
    List<Customer> findCustomerByEmailIdAndPassword(String custEmail, String password);

    @Query(value = "select * from ME_CUSTOMER where CUST_EMAIL = :custEmail", nativeQuery = true)
    List<Customer> findCustomerByEmailId(String custEmail);

}
